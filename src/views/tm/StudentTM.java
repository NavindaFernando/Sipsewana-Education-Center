package views.tm;

public class StudentTM {
    private String id;
    private String name;
    private String address;
    private String contact;
    private String age;

    public StudentTM() {

    }

    public StudentTM(String id, String name, String address, String contact, String age) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "StudentTM{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
