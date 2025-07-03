package com.moksha.todof.Service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KeyValueDTO {
    private String key;
    private Object value;

    public KeyValueDTO() {
    }

    public KeyValueDTO(String key, Object value) {
        this.key = key;
        this.value = value;
    }
}
