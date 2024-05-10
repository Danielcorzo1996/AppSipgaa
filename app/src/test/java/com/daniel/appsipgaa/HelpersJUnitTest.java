package com.daniel.appsipgaa;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class HelpersJUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }


    @Test
    public void checkFieldsOk_allFields_return_true(){
        Helpers helpers = new Helpers();
        String error = "";

        error = helpers.checkFieldsOk("italo12346","123456","italo@mail.com");
        assertEquals("", error);
    }

    @Test
    public void checkFieldsOk_withOutUser_return_true(){
        Helpers helpers = new Helpers();
        String error = "";

        error = helpers.checkFieldsOk(null,"123456","italo@mail.com");
        assertEquals("", error);
    }


    @Test
    public void checkFieldsOk_userBadAllField_return_false(){
        Helpers helpers = new Helpers();
        String error = "";

        error = helpers.checkFieldsOk("","1","1");
        assertEquals("Ingresa un Usuario", error);
    }

    @Test
    public void checkFieldsOk_passwordBadAllField_return_false(){
        IHelpers helpers = new Helpers();
        String error = "";

        error = helpers.checkFieldsOk("italo1234","","1");
        assertEquals("Ingresa una Clave", error);

        error = helpers.checkFieldsOk("italo1234","123","1");
        assertEquals("Clave minimo 6 caracteres!", error);
    }

    @Test
    public void checkFieldsOk_emailBadAllField_return_false(){
        Helpers helpers = new Helpers();
        String error = "";

        error = helpers.checkFieldsOk("italo1234","1234567","");
        assertEquals("Ingresa un Correo valido", error);

        error = helpers.checkFieldsOk("italo1234","1234567","italo");
        assertEquals("Ingresa un Correo valido", error);
    }


    @Test
    public void checkFieldsOk_passwordBadWithOutUser_return_false(){
        IHelpers helpers = new Helpers();
        String error = "";

        error = helpers.checkFieldsOk(null,"","test@mail.com");
        assertEquals("Ingresa una Clave", error);

        error = helpers.checkFieldsOk("italo1234","123","test@mail.com");
        assertEquals("Clave minimo 6 caracteres!", error);
    }

    @Test
    public void checkFieldsOk_emailBadWithOutUser_return_false(){
        Helpers helpers = new Helpers();
        String error = "";

        error = helpers.checkFieldsOk(null,"1234567","");
        assertEquals("Ingresa un Correo valido", error);

        error = helpers.checkFieldsOk("italo1234","1234567","italo");
        assertEquals("Ingresa un Correo valido", error);
    }
}