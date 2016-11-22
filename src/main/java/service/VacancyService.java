package service;

import dao.CategoryDAO;
import dao.CompanyDAO;
import dao.PositionDAO;
import dao.VacancyDAO;
import entity.*;
import jms.MessageSender;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

/**
 * Created by ignatenko on 19.11.16.
 */

@Stateless
@Local
public class VacancyService {

    private final static Logger LOG = Logger.getLogger(CompanyService.class);

    @EJB
    private VacancyDAO vacancyDAO;
    @EJB
    private CompanyDAO companyDAO;
    @EJB
    private CategoryDAO categoryDAO;
    @EJB
    private PositionDAO positionDAO;

    @EJB
    private MessageSender messageSender;

    public boolean save(String title, String date, String salary, String requirement, String companyID, String categoryID, String positionID) {
        try {
            Vacancy vacancy = new Vacancy(title, date, salary, requirement);
            //TODO check ID for not nulls!
            Company newCompany =  (companyID != null)? companyDAO.find(Long.valueOf(companyID)):null;
            Category newCategory =  (categoryID != null)? categoryDAO.find(Long.valueOf(categoryID)):null;
            Position newPosition =  (positionID != null)? positionDAO.find(Long.valueOf(positionID)):null;

            vacancy.setCompany(newCompany);
            vacancy.setCategory(newCategory);
            vacancy.setPosition(newPosition);
            vacancyDAO.save(vacancy);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void sendBroadcastMessage(String messageText){
        messageSender.send(messageText);
    }

    public boolean update(String vacancyId, String title, String date, String salary, String requirement, String companyID, String categoryID, String positionID){
        try {
            Vacancy vacancy= vacancyDAO.find(Long.valueOf(vacancyId));
            vacancy.setTitle(title);
            vacancy.setDate(date);
            vacancy.setSalary(salary);
            vacancy.setRequirement(requirement);

            Company chosenCompany =  (companyID != null)? companyDAO.find(Long.valueOf(companyID)):null;
            Category chosenCategory =  (categoryID != null)? categoryDAO.find(Long.valueOf(categoryID)):null;
            Position chosenPosition =  (positionID != null)? positionDAO.find(Long.valueOf(positionID)):null;


            vacancy.setCompany(chosenCompany);
            vacancy.setCategory(chosenCategory);
            vacancy.setPosition(chosenPosition);



            vacancyDAO.update(vacancy);
            return true;
        }catch (NumberFormatException nfe){
            LOG.error("fail to parse vacancy id");
            nfe.printStackTrace();
        }catch (Exception e){
            LOG.error("fail to update vacancy");
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(String id){
        try {
            Vacancy vacancy = vacancyDAO.find(Long.valueOf(id));
            Category category = vacancy.getCategory();
            if (category != null){
                category.getVacancies().remove(vacancy);
            }
            Company company = vacancy.getCompany();
            if (company != null){
                company.getVacancies().remove(vacancy);
            }
            vacancyDAO.delete(vacancy);
            return true;
        }catch (NumberFormatException nfe){
            LOG.error("fail to parse vacancy id");
            nfe.printStackTrace();
        }catch (Exception e){
            LOG.error("fail to delete vacancy");
            e.printStackTrace();
        }
        return false;
    }

    public Vacancy get(String id){
        try {
            return vacancyDAO.find(Long.valueOf(id));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Vacancy> getAllVacancies() {
        return util.Utils.toList(vacancyDAO.findAll());
    }

    public List<Company> getAllCompanies() {
        return util.Utils.toList(companyDAO.findAll());
    }
    public List<Category> getAllCategories() {
        return util.Utils.toList(categoryDAO.findAll());
    }

    public List<Position> getAllPositions() {
        return util.Utils.toList(positionDAO.findAll());
    }

    public boolean isVacancyhaveUser(Vacancy vacancy, User user){
        try {
            if (user.getVacancies().contains(vacancy)) {
                return true;
            }
        }catch(Exception e){
            LOG.error("fail to parse vacancy has user");
        }
        return false;
    }

    public boolean sendEmailWithGmailSMTP(String emailCompany, String emailUser, String companyName){
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("mkrexample@gmail.com","mkrexample123");
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("mkrexample@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(emailCompany));
            message.setSubject("New slave!");
            message.setText("Dear "+companyName+" This slave want to work for you. Answer him as fast " +
                    "as you can "+emailUser);

            Transport.send(message);

           LOG.info("Mail has alread sent");
            return true;

        } catch (MessagingException e) {
            LOG.info("Error in sending email", e);
        }

        return false;
    }
}
