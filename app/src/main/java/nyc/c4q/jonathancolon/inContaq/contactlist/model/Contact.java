package nyc.c4q.jonathancolon.inContaq.contactlist.model;

import org.parceler.Parcel;


// TODO: 1/13/17 remove Parceler
@Parcel
public class Contact {
    Long _id;
    long birthDate;
    String firstName;
    String lastName;
    String nickname;
    String homePhoneNumber;
    String workPhoneNumber;
    String cellPhoneNumber;
    String address;
    String email;
    int backgroundImage;
    int contactImage;
    Long timeLastContacted;


    public Contact() {
    }

    public Contact(String firstName) {
        this.firstName = firstName;
    }

    public Contact(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Contact(String firstName, String lastName, String cellPhoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cellPhoneNumber = cellPhoneNumber;
    }

    public Contact(String firstName, String lastName, int backgroundImage, int contactImage) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.backgroundImage = backgroundImage;
        this.contactImage = contactImage;
    }

    public int getContactImage() {
        return contactImage;
    }

    public void setContactImage(int contactImage) {
        this.contactImage = contactImage;
    }

    public int getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(int backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public long getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(long birthDate) {
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHomePhoneNumber() {
        return homePhoneNumber;
    }

    public void setHomePhoneNumber(String homePhoneNumber) {
        this.homePhoneNumber = homePhoneNumber;
    }

    public String getWorkPhoneNumber() {
        return workPhoneNumber;
    }

    public void setWorkPhoneNumber(String workPhoneNumber) {
        this.workPhoneNumber = workPhoneNumber;
    }

    public String getCellPhoneNumber() {
        return cellPhoneNumber;
    }

    public void setCellPhoneNumber(String cellPhoneNumber) {
        this.cellPhoneNumber = cellPhoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public long getTimeLastContacted() {
        return timeLastContacted;
    }

    public void setTimeLastContacted(long timeLastContacted) {
        this.timeLastContacted = System.currentTimeMillis();
    }

}
