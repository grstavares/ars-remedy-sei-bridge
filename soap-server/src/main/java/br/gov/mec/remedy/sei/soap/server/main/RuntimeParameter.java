package br.gov.mec.remedy.sei.soap.server.main;

public enum RuntimeParameter {

    SERVER, PORT;

    String asString() {

        switch (this) {
            case SERVER: return "--server=";
            case PORT: return "--port=";
        }

        return "undefined";

    }

}
