package com;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import model.Patient;
@Path("/Patient")
public class PatientTest {

Patient patientObj = new Patient();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	{
	return patientObj.readItems();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insert_patient(@FormParam("firstName") String patient_fname,
	 @FormParam("lastName") String patient_lname,
	 @FormParam("age") String patient_age,
	 @FormParam("gender") String patient_gender,
	 @FormParam("email") String patient_email,
	 @FormParam("phone") String patient_phone,
	 @FormParam("address") String patient_address)
	{
	 String output = patientObj.insert_patient(patient_fname, patient_lname, patient_age, patient_gender, patient_email, patient_phone, patient_address);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String update_patient(String patientData)
	{
	//Convert the input string to a JSON object
	 JsonObject patientObject = new JsonParser().parse(patientData).getAsJsonObject();
	//Read the values from the JSON object
	 String patient_id = patientObject.get("patient_id").getAsString();
	 String patient_fname = patientObject.get("patient_fname").getAsString();
	 String patient_lname = patientObject.get("patient_lname").getAsString();
	 String patient_age = patientObject.get("patient_age").getAsString();
	 String patient_gender = patientObject.get("patient_gender").getAsString();
	 String patient_email = patientObject.get("patient_email").getAsString();
	 String patient_phone = patientlObject.get("patient_phone").getAsString();
	 String patient_address = patientObject.get("patient_address").getAsString();
	
	 String output = patientObj.update_patient(patient_id, patient_fname, patient_lname, patient_age, patient_gender, patient_email, patient_phone, patient_address);
	return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String delete_patient(String patientData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(patientData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String patient_id = doc.select("patient_id").text();
	 String output = patientObj.delete_patient(patient_id);
	return output;
	}
}
