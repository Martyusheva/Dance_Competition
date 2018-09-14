package dancecomp.logics;

import dancecomp.mappers.CityMapper;
import dancecomp.mappers.ClubMapper;
import dancecomp.mappers.JudgeMapper;
import dancecomp.mappers.CompetitionMapper;

public class Competition {
    public static final int STATUS_NONE = 0;
    public static final int STATUS_WAITING_REVIEW = 1;
    public static final int STATUS_APPROVED = 2;
    public static final int STATUS_DENIED = 3;
    public static final int STATUS_IN_PROCESS = 4;
    public static final int STATUS_COMPLITED = 5;
    public static final int STATUS_REFUSED = 6;

    public static final String[] statusNames = {
            "-",
            "Ожидает проверки",
            "Утверждено",
            "Отказано",
            "В процессе",
            "Завершено",
            "Отменено"
    };

    protected int id;
    protected String date;
    protected String name;
    protected int cityId;
    protected int clubId;
    protected double cost;
    protected int status;
    protected int organizerId;
    protected int countingBoardId;
    protected int judgeMainId;
    protected int judge2Id;
    protected int judge3Id;
    protected int judge4Id;
    protected int judge5Id;
    protected boolean debut;
    protected boolean e;
    protected boolean d;
    protected boolean c;
    protected boolean b;
    protected boolean a;
    protected boolean abc;
    protected String comments;

    public Competition(
            int cityId,
            String name,
            int clubId,
            int organizerId,
            int countingBoardId,
            int judgeMainId,
            int judge2Id,
            int judge3Id,
            int judge4Id,
            int judge5Id,
            boolean debut,
            boolean e,
            boolean d,
            boolean c,
            boolean b,
            boolean a,
            boolean abc,
            String date
    ) {
        this.setCityId(cityId);
        this.setName(name);
        this.setClubId(clubId);
        this.setOrganizerId(organizerId);
        this.setStatus(Competition.STATUS_WAITING_REVIEW);
        this.setCost(0);
        this.setCountingBoardId(countingBoardId);
        this.setJudgeMainId(judgeMainId);
        this.setJudge2Id(judge2Id);
        this.setJudge3Id(judge3Id);
        this.setJudge4Id(judge4Id);
        this.setJudge5Id(judge5Id);
        this.setDedut(debut);
        this.setE(e);
        this.setD(d);
        this.setC(c);
        this.setB(b);
        this.setA(a);
        this.setAbc(abc);
        this.setComments("");

        this.setDate(date);

        this.setId(0);

        this.save();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCountingBoardId() {
        return countingBoardId;
    }

    public void setCountingBoardId(int countingBoardId) {
        this.countingBoardId = countingBoardId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(int organizerId) {
        this.organizerId = organizerId;
    }

    public int getJudgeMainId() {
        return judgeMainId;
    }

    public void setJudgeMainId(int judgeMainId){
        this.judgeMainId = judgeMainId;
    }

    public int getJudge2Id() {
        return judge2Id;
    }

    public void setJudge2Id(int judge2Id){
        this.judge2Id = judge2Id;
    }

    public int getJudge3Id() {
        return judge3Id;
    }

    public void setJudge3Id(int judge3Id){
        this.judge3Id = judge3Id;
    }

    public int getJudge4Id() {
        return judge4Id;
    }

    public void setJudge4Id(int judge4Id){
        this.judge4Id = judge4Id;
    }

    public int getJudge5Id() {
        return judge5Id;
    }

    public void setJudge5Id(int judge5Id){
        this.judge5Id = judge5Id;
    }

    public boolean getAbc() {
        return abc;
    }

    public void setAbc(boolean isSet){
        this.abc = isSet;
    }

    public boolean getA() {
        return a;
    }

    public void setA(boolean isSet){
        this.a = isSet;
    }

    public boolean getB() {
        return b;
    }

    public void setB(boolean isSet){
        this.b = isSet;
    }

    public boolean getC() {
        return c;
    }

    public void setC(boolean isSet){
        this.c = isSet;
    }

    public boolean getD() {
        return d;
    }

    public void setD(boolean isSet){
        this.d = isSet;
    }

    public boolean getE() {
        return e;
    }

    public void setE(boolean isSet){
        this.e = isSet;
    }

    public boolean getDebut() {
        return debut;
    }

    public void setDedut(boolean isSet){
        this.debut = isSet;
    }

    public String getDetails() {
        CityMapper.getById(this.clubId).getName();
        //"Стоимость: " + (this.getCost() > 0 ? this.getCost() + " руб." : " не доступна");
        return "Город: " + CityMapper.getById(this.cityId).getName() + "Организатор: " + ClubMapper.getById(this.clubId).getName() + "Судьи: " + JudgeMapper.getById(this.getJudgeMainId()) + "; " + JudgeMapper.getById(this.getJudge2Id()) + "; " + JudgeMapper.getById(this.getJudge3Id()) +
                "; " + JudgeMapper.getById(this.getJudge4Id()) + "; " + JudgeMapper.getById(this.getJudge5Id()) + ". Номинации: " + (this.getDebut() ? "Дебют, " : "") + (this.getE() ? "E, " : "") +
                (this.getD() ? "D, " : "") + (this.getC() ? "C, " : "") + (this.getB() ? "B, " : "") + (this.getA() ? "A, " : "") + (this.getAbc() ? "ABC. " : "");
    }

    public  void setComments (String comments){
        this.comments = comments;
    }

    public String getComments() {
        return comments;
    }

    public String getStatusName() {
        return statusNames[this.getStatus()];
    }

    public boolean save() {
        return CompetitionMapper.save(this);
    }

    public boolean remove() {
        return CompetitionMapper.remove(this);
    }
}
