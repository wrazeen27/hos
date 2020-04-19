package model;

public class Patient {

	public Connection connect() {

		Connection con = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcaremanagement?serverTimezone=UTC",
					"root", "");
			// For testing
			System.out.print("Successfully connected");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}
	
	public String insert_patient(String patient_fname, String patient_lname, int patient_age, String patient_gender, String patient_email, String patient_phone, String patient_address) {
		String output = "";

		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database";
			}
			// create a prepared statement
			String query = " insert into patient(patient_fname, patient_lname, patient_age, patient_gender, patient_email, patient_phone, patient_address) values(?,?,?,?,?,?,?,?,?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, patient_fname);
			preparedStmt.setString(3, patient_lname);
			preparedStmt.setInt(4, patient_age);
			preparedStmt.setString(5, patient_gender);
			preparedStmt.setString(6, patient_email);
			preparedStmt.setString(7, patient_phone);
			preparedStmt.setString(8, patient_address);

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String readItems() {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// Prepare the html table to be displayed
			output = " <table border=\"1\"><tr><th>ID</th><th>Patient First Name</th><th>Patient Last Name</th><th>Patient Age</th>"
					+ "<th>Patient Gender</th><th>Patient Email</th><th>Patient Phone</th><th>Patient Address</th>"
					+ "<th>Update</th><th>Remove</th></tr>";

			String query = "select * from patient";

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {
				
				String.patient_id(rs.getString("patient_id"));
				String.patient_fname(rs.getString("patient_fname"));
				String.patient_Lname(rs.getString("patient_Lname"));
				String.patient_age(rs.getString("patient_age"));
				String.Patient_gender(rs.getString("Patient_gender"));
				String.Patient_email(rs.getString("Patient_email"));
				String.Patient_phone(rs.getString("Patient_phone"));
				String.Patient_address(rs.getString("Patient_address"));
				
				// Add into the html table
				output += "<tr><td>" + patient_id + "</td>";
				output += "<td>" + patient_fname + "</td>";
				output += "<td>" + patient_Lname + "</td>";
				output += "<td>" + patient_age + "</td>";
				output += "<td>" + patient_gender + "</td>";
				output += "<td>" + patient_email + "</td>";
				output += "<td>" + patient_phone + "</td>";
				output += "<td>" + patient_address + "</td>";

				// buttons
				output += "<td><input name=\"btnUpdate\" " + " type=\"button\" value=\"Update\"></td>"
						+ "<td><form method=\"post\" action=\"items.jsp\">" + "<input name=\"btnRemove\" "
						+ " type=\"submit\" value=\"Remove\">" + "<input name=\"itemID\" type=\"hidden\" " + " value=\""
						+ patient_id + "\">" + "</form></td></tr>";
			}

			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String update_patient(String patient_fname, String patient_lname, int patient_age, String patient_gender, String patient_email, String patient_phone, String patient_address) {
		String output = "";
		
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			
			String query = "UPDATE patient SET patient_fname=?, patient_lname=?, patient_age=?, patient_gender=?, patient_email=?, patient_phone=?, patient_address=? WHERE patient_id=?";
			
			PreparedStatement preparedStmnt = con.prepareStatement(query);
			
			preparedStmnt.setString(1, patient_fname);
			preparedStmnt.setString(2, patient_lname);
			preparedStmnt.setInt(3, patient_age);
			preparedStmnt.setString(4, patient_gender);
			preparedStmnt.setString(5, patient_email);
			preparedStmnt.setString(6, patient_phone);
			preparedStmnt.setString(7, patient_address);
			
			preparedStmnt.setInt(8, patient_id);
			
			preparedStmnt.execute();
			con.close();
			
			output="Updated Successfully";
		}catch(Exception e) {
			output="error while updating the item.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	public String delete_patient(String patient_id){
		String output = "";
	try{
			Connection con = connect();
		 if (con == null){
			 return "Error while connecting to the database for deleting.";
		 }
		 
		 // create a prepared statement
		 String query = "delete from patient where patient_id=?";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(patient_id));
	
		 // execute the statement
		 preparedStmt.execute();
		 con.close();
		 output = "Deleted successfully";
		 }
	catch (Exception e)
	 {
		 output = "Error while deleting the item.";
		 System.err.println(e.getMessage());
	 }
	return output;
	}
}
