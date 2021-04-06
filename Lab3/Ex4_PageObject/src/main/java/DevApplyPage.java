import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class DevApplyPage {
    private WebDriver driver;

    @FindBy(tagName = "h1")
    WebElement heading;

    @FindBy(id="talent_create_applicant_email")
    WebElement email;

    @FindBy(id = "talent_create_applicant_password")
    WebElement password;

    @FindBy(id = "talent_create_applicant_password_confirmation")
    WebElement password_confirmation;

    @FindBy(id = "talent_create_applicant_full_name")
    WebElement full_name;

    @FindBy(id ="id=save_new_talent_create_applicant")
    WebElement join_toptal_button;


    //Constructor
    public DevApplyPage(WebDriver driver){
        this.driver=driver;

        //Initialise Elements
        PageFactory.initElements(driver, this);
    }

    public void setDeveloper_email(String mail){
        email.clear();
        email.sendKeys(mail);
    }

    public void setDeveloper_password(String pass){
        password.clear();
        password.sendKeys(pass);
    }

    public void  setDeveloper_password_confirmation(String password__confirmation){
        password_confirmation.clear();
        password_confirmation.sendKeys(password__confirmation);
    }

    public void setDeveloper_full_name (String fullname){
        full_name.clear();
        full_name.sendKeys(fullname);
    }

    public void clickOnJoin(){
        join_toptal_button.click();
    }
    public boolean isPageOpened(){
        //Assertion
        return heading.getText().toString().contains("Apply to Join\nthe World's Top Talent Network");
    }
}

//css=.is-active > .custom_select-options_item_text