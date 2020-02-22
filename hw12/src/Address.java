package edu.umb.cs.cs681.hw12;

public final class Address {
	private final String street, city, state;
	private final int zipcode;
	
	public Address (String st, String cty, String ste, String zip) {
		street = st;
		city = cty;
		state = ste;
		zipcode = Integer.parseInt(zip);
		
	}
	
	public String getStreet() {
		return street;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public int getZipcode() {
		return zipcode;
	}
	
	 public Address change(String street, String city, String state, String zipcode){
		 return new Address(street, city, state, zipcode);  } 
	 
	 public String toString() {
		 return street + ", " + city + ", " + state + ", " + String.valueOf(zipcode);
	 };
	 
	 
	 public boolean equals(Address add){
		 if (  this.toString().equals(add.toString()))
				 return true;
		 else {return false;}
		 
	 };
	 
	

}
