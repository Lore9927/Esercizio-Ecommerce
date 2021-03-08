package it.objectmethod.ecommerce.service.dto;

public class UtenteRequestDTO {
	private UtenteDTO utenteDTO;
	private String password;
	
	public UtenteDTO getUtenteDTO() {
		return utenteDTO;
	}
	public void setUtenteDTO(UtenteDTO utenteDTO) {
		this.utenteDTO = utenteDTO;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
