package ru.bellintegrator.servlet.person;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import ru.bellintegrator.servlet.base.BaseServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PersonServlet extends BaseServlet {
    private final Log log = LogFactory.getLog(getClass());

    /**
     * List person objects from database
     *
     * @param request
     * @param response persons list as JSON
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        StringBuffer requestUrl = request.getRequestURL();
        log.info("request: " + requestUrl + ";start");

        PersonDao dao = new PersonDaoImpl(dataSource());
        List<PersonView> persons = dao.all();

        log.info("request: " + requestUrl + ";persons loaded count:" + persons.size());

        writeResponse(response, persons);

        log.info("request: " + requestUrl + ";success");
    }

    /**
     * Save new person to database
     *
     * @param request  person data as JSON
     * @param response
     * @throws IOException
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuffer requestUrl = request.getRequestURL();
        log.info("request: " + requestUrl + ";start");

        String requestBody = readBody(request);
        log.debug("request:" + requestUrl + ";body:" + requestBody);

        ObjectMapper mapper = new ObjectMapper();
        PersonView person = mapper.readValue(requestBody, PersonView.class);

        log.info("request: " + requestUrl + ";mapping request body success");

        PersonDao dao = new PersonDaoImpl(dataSource());
        dao.add(person);

        log.info("request: " + requestUrl + ";success");
    }
}
