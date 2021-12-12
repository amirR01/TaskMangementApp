package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class User {
    {
        final User Admin = new User("admin", "admin", "admin");
        Admin.setRole("System Admin");
        users.add(Admin);
    }
    private static ArrayList<User> users = new ArrayList<>();
    private static int idCreator = 1;
    private int creationId;
    private String fullName;
    private Date birthday;
    private String userName;
    private String password;
    private String email;
    private String role = "Member";
    private int score;
    private ArrayList<Log> allLogs;
    private ArrayList<Task> allTasksForUser;
    private ArrayList<Notification> notifications;
    private ArrayList<Team> userTeams;
    private HashMap<Team, model.Date> joiningDate;

    public User(String userName, String password,
                String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.creationId = idCreator;
        idCreator++;
        users.add(this);
        this.allLogs = new ArrayList<>();
        this.allTasksForUser = new ArrayList<>();
        this.notifications = new ArrayList<>();
        this.userTeams = new ArrayList<>();
        this.joiningDate = new HashMap<>();
    }

    public static User getUserByUsername(String userName) {
        for (User user : users) {
            if (user.userName.equals(userName))
                return user;
        }
        return null;
    }

    public int getScore() {
        return score;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public int getCreationId() {
        return creationId;
    }

    public ArrayList<Task> getAllTasksForUser() {
        return allTasksForUser;
    }

    public ArrayList<Team> getUserTeams() {
        return userTeams;
    }

    public HashMap<Team, model.Date> getJoiningDate() {
        return joiningDate;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public ArrayList<Log> getAllLogs() {
        return allLogs;
    }

    public ArrayList<Notification> getNotifications() {
        return notifications;
    }

    public String getFullName() {
        return fullName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }
}