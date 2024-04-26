package com.daniel.appsipgaa;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class RegistroJUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void checkFieldsOk_return_true(){
        Helpers helpers = new Helpers();
        String error = "";

        error = helpers.checkFieldsOk("italo12346","123456","italo@mail.com");
        assertEquals("", error);
    }


    @Test
    public void checkFieldsOk_userBad_return_false(){
        Helpers helpers = new Helpers();
        String error = "";

        error = helpers.checkFieldsOk("","1","1");
        assertEquals("Ingresa un Usuario", error);
    }

    @Test
    public void checkFieldsOk_passwordBad_return_false(){
        Helpers helpers = new Helpers();
        String error = "";

        error = helpers.checkFieldsOk("italo1234","","1");
        assertEquals("Ingresa una Clave", error);

        error = helpers.checkFieldsOk("italo1234","123","1");
        assertEquals("Clave minimo 6 caracteres!", error);
    }

    @Test
    public void checkFieldsOk_emailBad_return_false(){
        Helpers helpers = new Helpers();
        String error = "";

        error = helpers.checkFieldsOk("italo1234","1234567","");
        assertEquals("Ingresa un Correo valido", error);

        error = helpers.checkFieldsOk("italo1234","1234567","italo");
        assertEquals("Ingresa un Correo valido", error);
    }
}