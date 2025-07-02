package com.moksha.todof.Exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GlobalExceptionHandlerTest {

   private GlobalExceptionHandler exceptionHandler;

   @BeforeEach
   void setUp() {
       exceptionHandler = new GlobalExceptionHandler();
   }

    @Test
    public void testHandleResourceNotFound() {
        String errorMessage = "Resource not found";

        ResourcesNotFoundException ex = new ResourcesNotFoundException(errorMessage);

        String response = exceptionHandler.handleResourceNotFound(ex);

        assertEquals(errorMessage, response);
    }

   @Test
    public void testHandleIllegalArgument() {
       String errorMessage = "Illegal argument";
       IllegalArgumentException exception = new IllegalArgumentException(errorMessage);

       String response = exceptionHandler.handleIllegalArgument(exception);

       assertEquals(errorMessage, response);
    }

    @Test
    public void testHandleGeneralException() {
        String errorMessage = "General error";
        Exception exception = new Exception(errorMessage);

        String response = exceptionHandler.handleGeneralException(exception);

        assertEquals("An unexpected error occurred: " + errorMessage, response);
    }
}
