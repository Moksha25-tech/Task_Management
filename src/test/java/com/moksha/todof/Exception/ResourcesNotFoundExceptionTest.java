package com.moksha.todof.Exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ResourcesNotFoundExceptionTest {
    @Test
    public void testExceptionMessage() {
        String errorMessage = "Resources not found";
        ResourcesNotFoundException exception = new ResourcesNotFoundException(errorMessage);

        assertEquals(errorMessage, exception.getMessage());
    }

    @Test
    public void testExceptionMessageAndCause() {
        String errorMessage = "Resources not found";
        Throwable throwable = new Throwable("Underlying cause");
        ResourcesNotFoundException exception = new ResourcesNotFoundException(errorMessage, throwable);

        assertEquals(errorMessage, exception.getMessage());
        assertEquals(throwable, exception.getCause());
    }

    @Test
    public void testExceptionWithoutCause() {
        String errorMessage = "Resources not found";
        ResourcesNotFoundException exception = new ResourcesNotFoundException(errorMessage);

        assertNull(exception.getCause());
    }
}
