package com.aruba.demoBollo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.aruba.demoBollo.beans.BolloDto;
import com.aruba.demoBollo.beans.TipoVeicolo;
import com.aruba.demoBollo.beans.VeicoloDto;
import com.aruba.demoBollo.controller.VeicoloRestController;
import com.aruba.demoBollo.service.VeicoloServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Mono;


@ExtendWith(SpringExtension.class)
@WebMvcTest(value = VeicoloRestController.class)

class DemoBolloApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private VeicoloServiceImpl veicoloService;
	
	@Test
	void getVeicoli() throws Exception {
	    
//	    ResultActions result1 
//	      = mockMvc.perform(post("http://localhost:8085/realms/SpringBootKeycloak/protocol/openid-connect/token")
//	        .params(params)
//	        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE))
//	        //.andExpect(status().isOk())
//	        .andExpect(content().contentType("application/json;charset=UTF-8"));
//	    
//	    String resultString = result1.andReturn().getResponse().getContentAsString();
	    AccessTokenDto atDto = getToken();
		VeicoloDto veicoloDto = new VeicoloDto();
		veicoloDto.setTarga("AB123CD");
		Mockito.when(veicoloService.getVeicoli(Mockito.anyString())).thenReturn(Arrays.asList(veicoloDto));
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/cars")
				.accept(MediaType.APPLICATION_JSON)
				.header("Authorization", "Bearer " + atDto.getAccessToken());

		MvcResult result = mockMvc.perform(requestBuilder)
				.andDo(print())
			    .andExpect(status().isOk())
			    .andReturn();

		VeicoloDto[] veicoloResult  = objectMapper.readValue(result.getResponse().getContentAsString(), VeicoloDto[].class);
		assertEquals(veicoloDto.getTarga(), veicoloResult[0].getTarga());
		
	}
	
	
	@Test
	void getVeicolo() throws Exception {
	    
	    AccessTokenDto atDto = getToken();
		VeicoloDto veicoloDto = new VeicoloDto();
		veicoloDto.setTarga("AB123CD");

		Mockito.when(veicoloService.getVeicolo(Mockito.startsWith("AB123CD"), Mockito.anyString())).thenReturn(veicoloDto);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/car/AB123CD")
				.accept(MediaType.APPLICATION_JSON)
				.header("Authorization", "Bearer " + atDto.getAccessToken());

		MvcResult result = mockMvc.perform(requestBuilder)
				.andDo(print())
			    .andExpect(status().isOk())
			    .andReturn();

		VeicoloDto veicoloResult  = objectMapper.readValue(result.getResponse().getContentAsString(), VeicoloDto.class);
		assertEquals(veicoloDto.getTarga(), veicoloResult.getTarga());
		
	}
	
	@Test
	void addVeicolo() throws Exception {
	    
	    AccessTokenDto atDto = getToken();
		VeicoloDto veicoloDto = new VeicoloDto();
		veicoloDto.setTarga("AB123CD");
		veicoloDto.setCodiceFiscale("PRGNRC76R25L219M");

		Mockito.when(veicoloService.addVeicolo(Mockito.any(VeicoloDto.class), Mockito.anyString())).thenReturn(veicoloDto);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/car")
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", "Bearer " + atDto.getAccessToken()).content(objectMapper.writeValueAsString(veicoloDto));

		MvcResult result = mockMvc.perform(requestBuilder)
				.andDo(print())
			    .andExpect(status().isCreated())
			    .andReturn();

		VeicoloDto veicoloResult  = objectMapper.readValue(result.getResponse().getContentAsString(), VeicoloDto.class);
		assertEquals(veicoloDto.getTarga(), veicoloResult.getTarga());
		
	}	
	
	@Test
	void addVeicolo2() throws Exception {
	    
	    AccessTokenDto atDto = getToken();
		VeicoloDto veicoloDto = new VeicoloDto();
		veicoloDto.setTarga("AB123C");
		veicoloDto.setCodiceFiscale("ASDFG");

		Mockito.when(veicoloService.addVeicolo(Mockito.any(VeicoloDto.class), Mockito.anyString())).thenReturn(veicoloDto);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/car")
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", "Bearer " + atDto.getAccessToken()).content(objectMapper.writeValueAsString(veicoloDto));

		MvcResult result = mockMvc.perform(requestBuilder)
				.andDo(print())
			    .andExpect(status().isBadRequest())
			    .andReturn();

		//assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		
	}
	
	@Test
	void updateVeicolo() throws Exception {
	    
	    AccessTokenDto atDto = getToken();
		VeicoloDto veicoloDto = new VeicoloDto();
		veicoloDto.setTarga("AB123CD");

		Mockito.when(veicoloService.updateVeicolo(Mockito.any(VeicoloDto.class), Mockito.anyString())).thenReturn(veicoloDto);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/car/AB123CD")
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", "Bearer " + atDto.getAccessToken()).content(objectMapper.writeValueAsString(veicoloDto));

		MvcResult result = mockMvc.perform(requestBuilder)
				.andDo(print())
			    .andExpect(status().isOk())
			    .andReturn();
		
		VeicoloDto veicoloResult  = objectMapper.readValue(result.getResponse().getContentAsString(), VeicoloDto.class);
		assertEquals(veicoloDto.getTarga(), veicoloResult.getTarga());

		
	}
	
	@Test
	void updateVeicolo2() throws Exception {
	    
	    AccessTokenDto atDto = getToken();
		VeicoloDto veicoloDto = new VeicoloDto();
		veicoloDto.setTarga("AB123CD");

		Mockito.when(veicoloService.updateVeicolo(Mockito.any(VeicoloDto.class), Mockito.anyString())).thenReturn(veicoloDto);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/car/AB456CD")
				.contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", "Bearer " + atDto.getAccessToken()).content(objectMapper.writeValueAsString(veicoloDto));

		MvcResult result = mockMvc.perform(requestBuilder)
				.andDo(print())
			    .andExpect(status().isBadRequest())
			    .andReturn();
		
//		VeicoloDto veicoloResult  = objectMapper.readValue(result.getResponse().getContentAsString(), VeicoloDto.class);
//		assertEquals(veicoloDto.getTarga(), veicoloResult.getTarga());

		
	}
	
	@Test
	void deleteVeicolo() throws Exception {
	    
	    AccessTokenDto atDto = getToken();
	    
	    //doNothing().when(veicoloService).deleteVeicolo(Mockito.anyString(), Mockito.anyString());
	    
	    RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/car/AB123CD")
				.accept(MediaType.APPLICATION_JSON)
				.header("Authorization", "Bearer " + atDto.getAccessToken());

		MvcResult result = mockMvc.perform(requestBuilder)
				.andDo(print())
			    .andExpect(status().isOk())
			    .andReturn();
		
		verify(veicoloService, times(1)).deleteVeicolo(Mockito.anyString(), Mockito.anyString());
		
	}
	
	@Test
	void getBolloVeicolo() throws Exception {
	    
	    AccessTokenDto atDto = getToken();
		BolloDto bolloDto = new BolloDto();
		bolloDto.setTarga("AB123CD");

		Mockito.when(veicoloService.retrieveBollo(Mockito.startsWith("AB123CD"), Mockito.any(TipoVeicolo.class))).thenReturn(bolloDto);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/car/AB123CD/AUTO/bollo")
				.accept(MediaType.APPLICATION_JSON)
				.header("Authorization", "Bearer " + atDto.getAccessToken());

		MvcResult result = mockMvc.perform(requestBuilder)
				.andDo(print())
			    .andExpect(status().isOk())
			    .andReturn();

//		VeicoloDto veicoloResult  = objectMapper.readValue(result.getResponse().getContentAsString(), VeicoloDto.class);
//		assertEquals(veicoloDto.getTarga(), veicoloResult.getTarga());
		
	}
	
	public AccessTokenDto getToken() {
		//{client_id: "login-app", username: username, password: password, grant_type: "password"}
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
	    params.add("grant_type", "password");
	    params.add("client_id", "login-app");
	    params.add("username", "enricopero");
	    params.add("password", "password");
	    
		URI tokenUri = UriComponentsBuilder.fromUriString("http://localhost:8085/realms/SpringBootKeycloak/protocol/openid-connect/token").build().toUri();
		WebClient webClient = WebClient.create();
		ResponseEntity<AccessTokenDto> response = webClient.post()
								  .uri(tokenUri)
								  .contentType(MediaType.APPLICATION_FORM_URLENCODED)
								  .body(BodyInserters.fromFormData(params))
								  .retrieve()
								    // Don't treat 404 responses as errors:
								    .onStatus(
								        status -> status.value() == 404,
								        clientResponse -> Mono.empty()
								    )
		
								    .toEntity(AccessTokenDto.class)
								    .block();
		
		if (response.getStatusCodeValue() == 404) {
			return null;
		} else {
			return response.getBody();
		}
	}
	
	

}
