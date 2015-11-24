/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Question;

import database.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;

/**
 *
 * @author Ahmad Naufal Farhan
 */
@WebService(serviceName = "QuestionsWS")
public class QuestionsWS {
    
    Connection conn = DB.getConnection();

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getQuestionById")
    @WebResult(name = "Question")
    public Question getQuestionById(@WebParam(name = "qid") int qid) {
        Question question = null;
        
        try (Statement st = conn.createStatement()) { 
                String query;
                query = "SELECT * FROM questions WHERE id = ?";
                
                // set the prepared statement by the query and enter the value of where clause
                PreparedStatement pst = conn.prepareStatement(query);
                pst.setInt(1, qid);
            
                // create a new question object with the result
            try (ResultSet res = pst.executeQuery()) {
                if (res.next())
                    question = new Question(res.getInt("id"),
                            res.getInt("uid"),
                            res.getString("topic"),
                            res.getString("content"),
                            res.getString("timestamp"));
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(QuestionsWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return question;
    }

    /**
     * Web service operation
     * @return 
     */
    @WebMethod(operationName = "getAllQuestions")
    @WebResult(name = "Questions")
    public List<Question> getAllQuestions() {
        List<Question> questions = new ArrayList<>();
        
        try (Statement st = conn.createStatement()) {
            
            String query = "SELECT * FROM `questions`";
            // set the prepared statement by the query and enter the value of where clause
            PreparedStatement pst = conn.prepareStatement(query);
               
            try (ResultSet res = pst.executeQuery()) {
                // get the questions
                while (res.next()) {
                    questions.add(new Question(res.getInt("id"),
                            res.getInt("uid"),
                            res.getString("topic"),
                            res.getString("content"),
                            res.getString("timestamp")));
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(QuestionsWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return questions;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "createQuestion")
    public int createQuestion(@WebParam(name = "token") String token,
                                @WebParam(name = "topic") String topic,
                                @WebParam(name = "content") String content) {
        
        int res = 0;
        
        /* TOKEN VALIDATION: Incomplete */
        int user_id = 7; /* get user by token: incomplete */
        
        try (Statement st = conn.createStatement()) {
            
            String query = "INSERT INTO `questions` (uid, topic, content) VALUES (?, ?, ?)";
            
            // set the prepared statement by the query and enter the value of where clause
            try (PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setInt(1, user_id);
                pst.setString(2, topic);
                pst.setString(3, content);
                // execute update
                res = pst.executeUpdate();
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(QuestionsWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "updateQuestion")
    public int updateQuestion(@WebParam(name = "token") String token,
                                @WebParam(name = "qid") int qid,
                                @WebParam(name = "newTopic") String newTopic,
                                @WebParam(name = "newContent") String newContent) {
        
        int res = 0;
        
        /* TOKEN VALIDATION: Incomplete */
        
        try (Statement st = conn.createStatement()) {
            
            String query = "UPDATE `questions` SET topic = ?, content = ? WHERE id = ?";
            
            // set the prepared statement by the query and enter the value of where clause
            try (PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setString(1, newTopic);
                pst.setString(2, newContent);
                pst.setInt(3, qid);
                // execute update
                res = pst.executeUpdate();
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(QuestionsWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "deleteQuestion")
    public int deleteQuestion(@WebParam(name = "token") String token,
                                @WebParam(name = "qid") int qid) {
        
        int res = 0;
        
        /* TOKEN VALIDATION: Incomplete */
        
        try (Statement st = conn.createStatement()) {
            
            String query = "DELETE FROM `questions` WHERE id = ?";
            
            // set the prepared statement by the query and enter the value of where clause
            try (PreparedStatement pst = conn.prepareStatement(query)) {
                pst.setInt(1, qid);
                // execute update
                res = pst.executeUpdate();
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(QuestionsWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }

    
}