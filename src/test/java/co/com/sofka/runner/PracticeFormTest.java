package co.com.sofka.runner;

import co.com.sofka.model.PracticeFormModel;
import co.com.sofka.page.PracticeFormPage;
import co.com.sofka.setup.WebUi;
import co.com.sofka.util.Gender;
import co.com.sofka.util.Hobbies;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class PracticeFormTest extends WebUi {

    private PracticeFormModel maria;
    private static final String ASSERTION_EXCEPTION_MESSAGE = "Los valores suministrados no son los esperados %s";
    private PracticeFormPage practiceFormPage;

    @BeforeEach
    public void setUp () {
        try {
            generateUserStudent();
            generalSetUp();
            maximize();
        } catch (Exception e) {
            quiteDriver();
            //e.printStackTrace();
        }

    }

    @Test
    public void practiceFormTestMamdatoryFields () {
        try {
            practiceFormPage = new PracticeFormPage(maria, super.driver);
            practiceFormPage.fillMandatoryFields();

            Assertions.assertEquals(
                    practiceFormPage.isRegistrationDone(),
                    elementsForRegister(),
                    String.format(ASSERTION_EXCEPTION_MESSAGE, outcome())
            );
        } catch (Exception e) {
            quiteDriver();
        }

    }

    @AfterEach
    public void tearDowm () throws InterruptedException {
        //driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        Thread.sleep(15000);
        quiteDriver();

    }

    private void generateUserStudent () {
        maria = new PracticeFormModel();
        maria.setName("Maria");
        maria.setLastName("Mora");
        maria.setEmail("moram@gmail.com");
        maria.setGender(Gender.FEMALE);
        maria.setMobile("3459876512");
        maria.setYear("1990");
        maria.setMonth("December");
        maria.setDay("11");
        maria.setHobbies(Arrays.asList(Hobbies.READING,Hobbies.MUSIC, Hobbies.SPORTS));
        maria.setCurrentAddres("Dg 75 dd 4-51 BL55 APTO 102");
        maria.setSubject(Arrays.asList("Maths", "History", "Biology"));
        maria.setPicture("C:\\Users\\JUANES\\Desktop\\DEmo\\src\\test\\resources\\images\\fondo.jpg");
        maria.setState("Rajasthan");
        maria.setCity("Jaipur");

    }

    public List<String> elementsForRegister () {
        List<String> submitFormResult = new ArrayList<>();
        submitFormResult.add((maria.getName() + " " + maria.getLastName()).trim());
        submitFormResult.add((maria.getGender().getValue()).trim());
        submitFormResult.add((maria.getMobile()).trim());

        return submitFormResult;
    }

    private String outcome () {

        return "\n " + practiceFormPage.isRegistrationDone().toString() + "\n\r" + elementsForRegister().toString();
    }
}
