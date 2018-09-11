package br.com.db1.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("lembrete")
public class LembreteRest {
	
	@GET
	@Path("olagalera/{parametro}")
	public Response exibirUmaMensagem() {
		return Response.status(200).entity("Ola galera fera").build();
	}
	
	
}
