package com.example.ms.ms_customer.constants;

//public class CustomersConstants {
//
//    private CustomersConstants(){
//
//    }
//
//    public static final String  STATUS_201 = "201";
//    public static final String  MESSAGE_201 = "Client created successfully";
//    public static final String  STATUS_200 = "200";
//    public static final String  MESSAGE_200 = "Request processed successfully";
//    public static final String  STATUS_417 = "417";
//    public static final String  MESSAGE_417_UPDATE= "Update operation failed. Please try again or contact Dev team";
//    public static final String  MESSAGE_417_DELETE= "Delete operation failed. Please try again or contact Dev team";
//    public static final String  STATUS_500 = "500";
//    public static final String  MESSAGE_500 = "An error occurred. Please try again or contact Dev team";
//}

/**
 * A utility class that contains the status codes and their corresponding messages
 * used throughout the customer-related operations. These constants are used for
 * API responses to ensure consistency in the application.
 */
public class CustomersConstants {

    // Private constructor to prevent instantiation
    private CustomersConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    // Success responses
    public static final String STATUS_201 = "201";  // Created
    public static final String MESSAGE_201 = "Client created successfully";

    public static final String STATUS_200 = "200";  // OK
    public static final String MESSAGE_200 = "Request processed successfully";

    public static final String STATUS_400 = "400";
    public static final String MESSAGE_400 = "Customer already exists with given DNI";

    // Error responses for various failure scenarios
    public static final String STATUS_417 = "417";  // Expectation Failed
    public static final String MESSAGE_417_UPDATE = "Update operation failed. Please try again or contact the Dev team";
    public static final String MESSAGE_417_DELETE = "Delete operation failed. Please try again or contact the Dev team";

    public static final String STATUS_500 = "500";  // Internal Server Error
    public static final String MESSAGE_500 = "An error occurred. Please try again or contact the Dev team";





}
