package ro.agitman.moe.web.action;

import org.mentawai.core.BaseAction;
import ro.agitman.moe.dao.ExamDao;
import ro.agitman.moe.dao.ExamItemAnswerDao;
import ro.agitman.moe.dao.ExamItemDao;
import ro.agitman.moe.dao.UserDao;
import ro.agitman.moe.model.Exam;
import ro.agitman.moe.model.ExamItem;
import ro.agitman.moe.model.ExamItemAnswer;
import ro.agitman.moe.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by d-uu31cq on 08.07.2016.
 */
public class AdminAction extends BaseAction {

    private final UserDao userDao;
    private final ExamDao examDao;
    private final ExamItemDao examItemDao;
    private final ExamItemAnswerDao itemAnswerDao;

    private final List<String> roles = new ArrayList<String>(Arrays.asList("STUDENT", "PROFESOR", "ADMIN"));

    public AdminAction(UserDao userDao, ExamDao examDao, ExamItemDao examItemDao, ExamItemAnswerDao itemAnswerDao) {
        this.userDao = userDao;
        this.examDao = examDao;
        this.examItemDao = examItemDao;
        this.itemAnswerDao = itemAnswerDao;
    }

    public String insertBacInfoSNSub1(){
        Exam exam = new Exam();
        exam.setName("BAC Info SN Subiectul I");
        exam.setOwner(1000);
        exam.setDifficulty(2);
        exam.setItems(5);
        exam.setPoints(30);
        exam.setLocked(false);

        exam = examDao.insert(exam);

//         Problema 1
        ExamItem ei = new ExamItem();
        ei.setExamid(exam.getId());
        ei.setTitle("Expresia C/C++ are valoarea...");
        ei.setAssertion("Expresia C/C++ <b>3+5%10/2</b> are valoarea:");
        ei.setOrd(1);
        ei.setPoints(4);
        ei.setType(1);

        ei = examItemDao.insert(ei);
        insertAnswer(ei, "3", false);
        insertAnswer(ei, "4", false);
        insertAnswer(ei, "5", true);
        insertAnswer(ei, "5.5", false);

//      Problema 2
        ei = new ExamItem();
        ei.setExamid(exam.getId());
        ei.setTitle("<b>Se consideră algoritmul alăturat...");
        ei.setAssertion("<b>Se consideră algoritmul alăturat, reprezentat în pseudocod.</b> S-a notat cu a%b restul împăr\u000Firii numărului " +
                "natural a la numărul natural nenul b si cu [c] partea întreagă a numărului real c." +
                "citeste n,k\n" +
                "(numere naturale nenule, k>1)\n" +
                "pn\u00010\n" +
                "┌cât timp pn=0 execută\n" +
                "│ x\u0001n\n" +
                "│┌cât timp x%k=0 execută\n" +
                "││ x\u0001[x/k]\n" +
                "│└■\n" +
                "│┌dacă x=1 atunci\n" +
                "││ pn\u0001n\n" +
                "│└■\n" +
                "│ n\u0001n-1\n" +
                "└■\n" +
                "scrie pn.\n" +
                "Scrie\u000Fi valoarea afisată dacă se citesc, în această ordine, numerele 48 și 6.");
        ei.setOrd(2);
        ei.setPoints(6);
        ei.setType(3);
        examItemDao.insert(ei);

        ei = new ExamItem();
        ei.setExamid(exam.getId());
        ei.setTitle("<b>Se consideră algoritmul alăturat...");
        ei.setAssertion("<b>Se consideră algoritmul alăturat, reprezentat în pseudocod.</b> S-a notat cu a%b restul împăr\u000Firii numărului " +
                "natural a la numărul natural nenul b si cu [c] partea întreagă a numărului real c." +
                "citeste n,k\n" +
                "(numere naturale nenule, k>1)\n" +
                "pn\u00010\n" +
                "┌cât timp pn=0 execută\n" +
                "│ x\u0001n\n" +
                "│┌cât timp x%k=0 execută\n" +
                "││ x\u0001[x/k]\n" +
                "│└■\n" +
                "│┌dacă x=1 atunci\n" +
                "││ pn\u0001n\n" +
                "│└■\n" +
                "│ n\u0001n-1\n" +
                "└■\n" +
                "scrie pn.\n" +
                "Dacă pentru k se citeste numărul 5, scrie\u000Fi toate numerele care pot fi citite pentru n\n" +
                "astfel încât, în urma executării algoritmului, pentru fiecare dintre acestea, valoarea afisată să fie 1.");
        ei.setOrd(3);
        ei.setPoints(4);
        ei.setType(3);
        examItemDao.insert(ei);

        ei = new ExamItem();
        ei.setExamid(exam.getId());
        ei.setTitle("<b>Se consideră algoritmul alăturat...");
        ei.setAssertion("<b>Se consideră algoritmul alăturat, reprezentat în pseudocod.</b> S-a notat cu a%b restul împăr\u000Firii numărului " +
                "natural a la numărul natural nenul b si cu [c] partea întreagă a numărului real c." +
                "citeste n,k\n" +
                "(numere naturale nenule, k>1)\n" +
                "pn\u00010\n" +
                "┌cât timp pn=0 execută\n" +
                "│ x\u0001n\n" +
                "│┌cât timp x%k=0 execută\n" +
                "││ x\u0001[x/k]\n" +
                "│└■\n" +
                "│┌dacă x=1 atunci\n" +
                "││ pn\u0001n\n" +
                "│└■\n" +
                "│ n\u0001n-1\n" +
                "└■\n" +
                "scrie pn.\n" +
                "Scrie\u000Fi în pseudocod un algoritm, echivalent cu cel dat, înlocuind prima structură cât\n" +
                "timp...execută cu o structură repetitivă de alt tip.");
        ei.setOrd(4);
        ei.setPoints(6);
        ei.setType(3);
        examItemDao.insert(ei);

        ei = new ExamItem();
        ei.setExamid(exam.getId());
        ei.setTitle("<b>Se consideră algoritmul alăturat...");
        ei.setAssertion("<b>Se consideră algoritmul alăturat, reprezentat în pseudocod.</b> S-a notat cu a%b restul împăr\u000Firii numărului " +
                "natural a la numărul natural nenul b si cu [c] partea întreagă a numărului real c." +
                "citeste n,k\n" +
                "(numere naturale nenule, k>1)\n" +
                "pn\u00010\n" +
                "┌cât timp pn=0 execută\n" +
                "│ x\u0001n\n" +
                "│┌cât timp x%k=0 execută\n" +
                "││ x\u0001[x/k]\n" +
                "│└■\n" +
                "│┌dacă x=1 atunci\n" +
                "││ pn\u0001n\n" +
                "│└■\n" +
                "│ n\u0001n-1\n" +
                "└■\n" +
                "scrie pn.\n" +
                "Scrie\u000Fi programul C/C++ corespunzător algoritmului dat.");
        ei.setOrd(5);
        ei.setPoints(10);
        ei.setType(3);
        examItemDao.insert(ei);

        return SUCCESS;
    }

    private void insertAnswer(ExamItem item, String value, Boolean correct){
        ExamItemAnswer answer = new ExamItemAnswer();
        answer.setItemid(item.getId());
        answer.setValue(value);
        answer.setCorrect(correct);
        itemAnswerDao.insert(answer);

    }

    public String saveUser() {

        if (isPost()) {
            User user = (User) input.getValue("user");
            if (user == null) {
                return ERROR;
            }
            if (user.getId() != null) {
                userDao.update(user);
            } else {
                userDao.insert(user);
            }
        }
        return SUCCESS;
    }

    public String editUser() {
        Integer userId = input.getInt("id");

        if (userId < 1000) {
            return ERROR;
        }

        User user = userDao.findById(userId);
        if (user == null) {
            return ERROR;
        }

        output.setValue("user", user);
        output.setValue("roles", roles);

        return SUCCESS;
    }

    public String newUser() {
        output.setValue("roles", roles);
        return SUCCESS;
    }

}
