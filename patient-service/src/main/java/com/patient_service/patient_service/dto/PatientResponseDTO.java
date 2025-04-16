package com.patient_service.patient_service.dto;

public class PatientResponseDTO {
    private String id;
    private String name;
    private String email;
    private String address;
    private String dateOfBirth;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}

//If we notice the entities returned are all string types and not in their defined types like UUID for id and all
//the main idea is that we need the response in JSON format and when we use the defined datatype itself , it is gonna soemtimes cause
//issues in converting these complex datatypes to JSON strings , the DTO solves this
//also we did not need to return each and every attribute like the registeredDate is not returned , it is just stored in db for auditing reasons