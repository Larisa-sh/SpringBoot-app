package com.example.springbootapp.activeMQ;

import java.io.Serializable;

public record Message(String text) implements Serializable {
}
