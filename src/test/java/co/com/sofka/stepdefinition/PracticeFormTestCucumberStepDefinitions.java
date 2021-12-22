package co.com.sofka.stepdefinition;

import co.com.sofka.model.PracticeFormModel;
import co.com.sofka.page.PracticeFormPage;
import co.com.sofka.setup.WebUi;
import co.com.sofka.util.Gender;
import co.com.sofka.util.Hobbies;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PracticeFormTestCucumberStepDefinitions  extends WebUi {

    private PracticeFormModel maria;
    private static final String ASSERTION_EXCEPTION_MESSAGE = "Los valores suministrados no son los esperados %s";
    private PracticeFormPage practiceFormPage;
    private List<String> comparator;

    @Given("que el empleado administrativo se encuentra en la página web de los ingresos de estudiantes")
    public void queElEmpleadoAdministrativoSeEncuentraEnLaPáginaWebDeLosIngresosDeEstudiantes() {
        try {
            generalSetUp();
            maximize();
        } catch (Exception e) {
            quiteDriver();
        }

    }
    @When("el empleado administrativo ingresa los campos obligatios y confirma la acción")
    public void elEmpleadoAdministrativoIngresaLosCamposObligatiosYConfirmaLaAcción() {
        try {
            generateUserStudent();
        } catch (Exception exception) {
            quiteDriver();
            Assertions.fail(exception.getMessage(),exception);
        }
    }
    @Then("el sistema deberá mostrar por la pantalla de registro del estudiante ingresado")
    public void elSistemaDeberáMostrarPorLaPantallaDeRegistroDelEstudianteIngresado() {
        try {
            practiceFormPage = new PracticeFormPage(maria, super.driver);
            practiceFormPage.fillMandatoryFields();
            comparator= new ArrayList<String>();
            comparator= practiceFormPage.isRegistrationDone();
            quiteDriver();
            Assertions.assertEquals(
                    comparator,
                    elementsForRegister(),
                    String.format(ASSERTION_EXCEPTION_MESSAGE, outcome())
            );
        } catch (Exception exception) {
            quiteDriver();
            Assertions.fail(exception.getMessage(),exception);
        }
    }

    private void generateUserStudent () {
        maria = new PracticeFormModel();
        maria.setName("Maria");
        maria.setLastName("Mora");
        maria.setEmail("moram@gmail.com");
        maria.setGender(Gender.FEMALE);
        maria.setMobile("3459876512");
        maria.setYear("1990");
        maria.setMonth("November");
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
        return "\n " + comparator.toString() + "\n\r" + elementsForRegister().toString();
    }
}
