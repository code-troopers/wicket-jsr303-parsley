package codetroopers.wicket.web.parsley;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
* User: cgatay
* Date: 28/04/13
* Time: 11:27
*/
public class TestBean {
    @NotNull
    private String attr1;
    @Min(4)
    private Long attr2;

    @NotNull
    @Max(5)
    private Long attr3;

    @Email
    private String emailAddress;

    @Size(min = 3, max = 54)
    private String sizeAttr;

    @Pattern(regexp = "[a-zA-Z]")
    private String patternAttr;

    public String getAttr1() {
        return attr1;
    }

    public void setAttr1(String attr1) {
        this.attr1 = attr1;
    }

    public Long getAttr2() {
        return attr2;
    }

    public void setAttr2(Long attr2) {
        this.attr2 = attr2;
    }

    public Long getAttr3() {
        return attr3;
    }

    public void setAttr3(Long attr3) {
        this.attr3 = attr3;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getSizeAttr() {
        return sizeAttr;
    }

    public void setSizeAttr(String sizeAttr) {
        this.sizeAttr = sizeAttr;
    }

    public String getPatternAttr() {
        return patternAttr;
    }

    public void setPatternAttr(final String patternAttr) {
        this.patternAttr = patternAttr;
    }
}
