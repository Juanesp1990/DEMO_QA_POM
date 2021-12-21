package co.com.sofka.page;

import co.com.sofka.model.PracticeFormModel;
import co.com.sofka.page.common.CommonActionOnpages;
import co.com.sofka.util.Hobbies;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.apache.logging.log4j.core.config.plugins.util.ResolverUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;


public class PracticeFormPage extends CommonActionOnpages{

    private final PracticeFormModel practiceFormModel;
    public static Log log = LogFactory.getLog(ResolverUtil.Test.class);


    // for input test cases
    private final By name = By.id("firstName");
    private final By lastName = By.id("lastName");
    private final By email = By.id("userEmail");
    private final By genderMale = By.cssSelector("#gender-radio-1+label");
    private final By genderFemale = By.cssSelector("#gender-radio-2+label");
    private final By genderOther = By.cssSelector("#gender-radio-3+label");
    private final By mobile = By.id("userNumber");
    private final By year = By.className("react-datepicker__year-select");
    private final By month = By.className("react-datepicker__year-select");
    private final By dateOfBirth = By.id("dateOfBirthInput");
    private final By subjectsLocator = By.id("subjectsInput");
    private final By sportsLocator = By.xpath("//*[@id=\"hobbiesWrapper\"]/div[2]/div[1]");
    private final By readingLocator = By.xpath("//*[@id=\"hobbiesWrapper\"]/div[2]/div[2]");
    private final By musicLocator = By.xpath("//*[@id=\"hobbiesWrapper\"]/div[2]/div[3]");
    private final By pictureLocator = By.id("uploadPicture");
    private final By addressLocator = By.id("currentAddress");
    private final By stateLocator = By.cssSelector("input[id='react-select-3-input']");
    private final By cityLocator = By.cssSelector("input[id='react-select-4-input']");
    private final By submit = By.id("submit");

    // for validations
    private final By nameStudent = By.xpath("/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[1]/td[2]");
    private final By genderStudent = By.xpath("/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[3]/td[2]");
    private final By mobileStudent = By.xpath("/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[4]/td[2]");


    // Funtions
    public PracticeFormPage (PracticeFormModel practiceFormModel, WebDriver webDriver) {
        super(webDriver);
        this.practiceFormModel = practiceFormModel;

    }

    public void fillMandatoryFields () {

        clearText(name);
        typeInto(name, practiceFormModel.getName());

        clearText(lastName);
        typeInto(lastName, practiceFormModel.getLastName());

        clearText(email);
        typeInto(email, practiceFormModel.getEmail());

        switch (practiceFormModel.getGender()) {
            case FEMALE:
                click(genderFemale);
                break;
            case MALE:
                click(genderMale);
                break;
            case OTHER:
                click(genderOther);
                break;
            default:
        }

        clearText(mobile);
        typeInto(mobile, practiceFormModel.getMobile());


      scrollDown();
        click(dateOfBirth);
        click(year);
        typeInto(year, practiceFormModel.getYear());
        click(month);
        typeInto(month, practiceFormModel.getMonth());
        pressEnter(month);
        By day = By.className("react-datepicker__day--0" + practiceFormModel.getDay());
        click(day);

        //Subjects
        List listSubjects = practiceFormModel.getSubject();
        for (int i = 0; i < listSubjects.size(); i++) {
            typeInto(subjectsLocator, (String) listSubjects.get(i));
            pressEnter(subjectsLocator)
            ;
        }

        List listHobbies = practiceFormModel.getHobbies();
        for (int x = 0; x < listHobbies.size(); x++){
            switch ((Hobbies) listHobbies.get(x)){
                case SPORTS:
                    click(sportsLocator);
                    break;
                case READING:
                    click(readingLocator);
                    break;
                case MUSIC:
                    click(musicLocator);
                    break;
            }

        }


        pathFile(pictureLocator, practiceFormModel.getPicture());

        // Address
        typeInto(addressLocator, practiceFormModel.getCurrentAddres());


        //State
        typeInto(stateLocator, practiceFormModel.getState());
        pressEnter(stateLocator);

        //City
        typeInto(cityLocator, practiceFormModel.getCity());
        pressEnter(cityLocator);



        click(submit);

        /* log.debug("Log desde debug");
        log.info("log desde info");
        log.warn("log desde warn");
        log.error("log desde error  ");
        log.fatal("log desde fatal");*/

    }

    public List<String> isRegistrationDone(){
        List<String> submitFormResult= new ArrayList<>();
        submitFormResult.add(getText(nameStudent).trim());
        submitFormResult.add(getText(genderStudent).trim());
        submitFormResult.add(getText(mobileStudent).trim());

        return submitFormResult;
    }

}
