package co.com.sofka.page;

import co.com.sofka.model.PracticeFormModel;
import co.com.sofka.page.common.CommonActionOnpages;
import co.com.sofka.util.Hobbies;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

import static io.cucumber.messages.internal.com.google.common.base.StandardSystemProperty.USER_DIR;

public class PracticeFormPage extends CommonActionOnpages {

    private final PracticeFormModel practiceFormModel;
    // for input test cases
    @CacheLookup
    @FindBy(id = "firstName" )
    private  WebElement  name;

    @CacheLookup
    @FindBy(id = "lastName" )
    private  WebElement  lastName;

    @CacheLookup
    @FindBy(id = "userEmail" )
    private WebElement email;

    @CacheLookup
    @FindBy(css= "#gender-radio-1+label")
    private WebElement genderMale ;

    @CacheLookup
    @FindBy(css = "#gender-radio-2+label" )
    private WebElement  genderFemale;

    @CacheLookup
    @FindBy(css = "#gender-radio-3+label")
    private WebElement genderOther ;

    @CacheLookup
    @FindBy(id = "userNumber" )
    private WebElement mobile;

    private final By dateOfBirth = By.id("dateOfBirthInput");
    String locatorYearMonth = "//option[.='%s']";
    String locatorDayMonth = "//div[contains(@aria-label,'%s') and contains(@aria-label,'%s')]";

    private final By subjectsLocator = By.id("subjectsInput");
    private final By sportsLocator = By.xpath("//*[@id=\"hobbiesWrapper\"]/div[2]/div[1]");
    private final By readingLocator = By.xpath("//*[@id=\"hobbiesWrapper\"]/div[2]/div[2]");
    private final By musicLocator = By.xpath("//*[@id=\"hobbiesWrapper\"]/div[2]/div[3]");

    private final By pictureLocator = By.id("uploadPicture");

    private final By addressLocator = By.id("currentAddress");
    private final By stateLocator = By.cssSelector("input[id='react-select-3-input']");
    private final By cityLocator = By.cssSelector("input[id='react-select-4-input']");

    //Sikulix elements.
    private static final String ATTACHMENT_FILE_PATCH = USER_DIR.value() + "\\src\\test\\resources\\images\\fondo.jpg";

    private static final String PAGE_BASE_PATCH = USER_DIR.value() + "\\src\\main\\resources\\page\\practiceform\\";
    private static final String SELECT_PICTURE_PATCH = PAGE_BASE_PATCH + "selectPicture.PNG";
    private static final String SELECT_OPEN_PATCH = PAGE_BASE_PATCH + "openWindows.PNG";
    private static final String FILE_NAME_TEXT_BOX_PATCH = PAGE_BASE_PATCH + "fileNameWindows.PNG";


    @CacheLookup
    @FindBy(id = "submit")
    private WebElement submit;

    // for validations
    private final By nameStudent = By.xpath("/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[1]/td[2]");
    private final By genderStudent = By.xpath("/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[3]/td[2]");
    private final By mobileStudent = By.xpath("/html/body/div[4]/div/div/div[2]/div/table/tbody/tr[4]/td[2]");


 //construtor
    public PracticeFormPage (PracticeFormModel practiceFormModel, WebDriver webDriver) {
        super(webDriver);
        this.practiceFormModel = practiceFormModel;
        PageFactory.initElements(webDriver,this);

    }


    // Funtions
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
        By year = By.xpath(String.format(locatorYearMonth, practiceFormModel.getYear()));
        click(year);
        By month = By.xpath(String.format(locatorYearMonth, practiceFormModel.getMonth()));
        click(month);
        By day = By.xpath(String.format(locatorDayMonth,practiceFormModel.getDay(),practiceFormModel.getMonth()));
        click(day);

        //Subjects
        List listSubjects = practiceFormModel.getSubject();
        for (Object listSubject : listSubjects) {
            typeInto(subjectsLocator, (String) listSubject);
            pressEnter(subjectsLocator)
            ;
        }

        List listHobbies = practiceFormModel.getHobbies();
        for (Object listHobby : listHobbies) {
            switch ((Hobbies) listHobby) {
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

        //pathFile(pictureLocator, practiceFormModel.getPicture());
        click(SELECT_PICTURE_PATCH);
        typeInto(FILE_NAME_TEXT_BOX_PATCH,ATTACHMENT_FILE_PATCH);
        click(SELECT_OPEN_PATCH);

        /* Address */
        typeInto(addressLocator, practiceFormModel.getCurrentAddres());

        /* State */
        typeInto(stateLocator, practiceFormModel.getState());
        pressEnter(stateLocator);

        /* City */
        typeInto(cityLocator, practiceFormModel.getCity());
        pressEnter(cityLocator);

        click(submit);


    }

    public List<String> isRegistrationDone () {
        List<String> submitFormResult = new ArrayList<>();
        submitFormResult.add(getText(nameStudent).trim());
        submitFormResult.add(getText(genderStudent).trim());
        submitFormResult.add(getText(mobileStudent).trim());
        return submitFormResult;
    }

}
