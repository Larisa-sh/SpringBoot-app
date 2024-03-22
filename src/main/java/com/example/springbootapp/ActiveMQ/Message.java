package com.example.springbootapp.ActiveMQ;

import java.io.Serializable;

public record Message(String text) implements Serializable {
}
