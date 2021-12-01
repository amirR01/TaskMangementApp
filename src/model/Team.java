package model;

import java.util.ArrayList;

public class Team {

    private static ArrayList<Team> acceptedTeams = new ArrayList<>();
    private static ArrayList<Team> pendingTeams = new ArrayList<>();
    private static int teamNumberCreator = 1;
    private int teamNumber;
    private String teamName;
    private ArrayList<User> teamMembers;
    private ArrayList<User> suspendedMembers;
    private User teamLeader;
    private Scoreboard scoreboard;
    private RoadMap roadMap;
    private ChatRoom chatRoom;
    private ArrayList<Task> allTasks;
    private ArrayList<Notification> notifications;

    public Team(String teamName, User teamLeader) {
        this.teamName = teamName;
        this.teamLeader = teamLeader;
        this.teamNumber = teamNumberCreator;
        teamNumberCreator++;
        pendingTeams.add(this);
        this.teamMembers = new ArrayList<>();
        this.allTasks = new ArrayList<>();
        this.suspendedMembers = new ArrayList<>();
        this.notifications = new ArrayList<>();
    }

    public void setScoreboard(Scoreboard scoreboard) {
        this.scoreboard = scoreboard;
    }

    public void setRoadMap(RoadMap roadMap) {
        this.roadMap = roadMap;
    }

    public void setChatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }

    public static ArrayList<Team> getAcceptedTeams() {
        return acceptedTeams;
    }

    public static ArrayList<Team> getPendingTeams() {
        return pendingTeams;
    }

    public ArrayList<Notification> getNotifications() {
        return notifications;
    }

    public int getTeamNumber() {
        return teamNumber;
    }

    public String getTeamName() {
        return teamName;
    }

    public ArrayList<User> getTeamMembers() {
        return teamMembers;
    }

    public User getTeamLeader() {
        return teamLeader;
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public RoadMap getRoadMap() {
        return roadMap;
    }

    public ChatRoom getChatRoom() {
        return chatRoom;
    }

    public ArrayList<Task> getAllTasks() {
        return allTasks;
    }
}