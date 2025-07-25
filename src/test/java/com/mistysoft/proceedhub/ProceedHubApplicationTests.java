package com.mistysoft.proceedhub;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProceedHubApplicationTests {

    @Test
    void contextLoads() {
        // Este metodo no esta implementado porque no hay logica de prueba especifica,
        // solo se asegura que el contexto de la aplicacion se inicie correctamente al ejecutar la aplicacion.
        if (Boolean.getBoolean("thorwException")) {
            throw new UnsupportedOperationException("Metodo no implementado (No sea cargó contextLoad)");
        }
    }

}