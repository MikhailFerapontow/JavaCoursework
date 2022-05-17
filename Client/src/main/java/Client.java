
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Client {

    static RestTemplate restTemplate = new RestTemplate();
    static HttpHeaders header = new HttpHeaders();
    private static String token;
    static HttpEntity<String> entity;

    static Scanner in = new Scanner(System.in);

    private static void getById(String url){
        System.out.println("Enter id:");
        String id = in.next();
        if (!id.matches("[0-9]+")) {
            System.out.println("Invalid id");
            return;
        }
        try {
            ResponseEntity<Object> obj = restTemplate.exchange(url + id, HttpMethod.GET, entity, Object.class);
            System.out.println(obj.getBody().toString());
        } catch (Exception e) {
            System.out.println("Object wasn't found");
            return;
        }
    }

    private static void add(String url, JSONObject jsonObject){
        HttpEntity request = new HttpEntity<>(jsonObject.toString(), header);
        try{
            Object obj = restTemplate.postForObject(url, request, Object.class);
            System.out.println(obj.toString());
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Not created");
            return;
        }
        System.out.println("Successfully added");
    }

    public static void signIn() throws JSONException {
        System.out.println("username: ");
        String name = in.next();
        System.out.println("password: ");
        String password = in.next();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userName", name);
        jsonObject.put("password", password);

        HttpEntity<String> request = new HttpEntity<>(jsonObject.toString(), header);
        try {
            String object = restTemplate.postForObject("http://localhost:8080/auth/signin", request, String.class);
            if (object != null) {
                token = object;
                header.set("Authorization", "Bearer " + token);
                entity = new HttpEntity<>(null, header);
                System.out.println(token);
            }
        } catch(Exception e){
            System.out.println("Wrong password or username");
        }
    }

    public static void getAllJournals(){
        ResponseEntity<List> list = restTemplate.getForEntity("http://localhost:8080/journal/getAll", List.class);
        for (Object obj : Objects.requireNonNull(list.getBody())){
            System.out.println(obj.toString() + ",\n");
        }
    }

    public static void getJournalById(){
        getById("http://localhost:8080/journal/get/");
    }

    public static void addJournal() throws JSONException{
        String intRegex = "[0-9]+";
        String timeRegex = "[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])T(2[0-3]|[01][0-9]):[0-5][0-9]:[0-5][0-9]";

        System.out.println("Enter book id");
        String idBook = in.next();
        if(!idBook.matches(intRegex)){
            System.out.println("Invalid id");
            return;
        }

        System.out.println("Enter client id:");
        String idClient = in.next();
        if(!idClient.matches(intRegex)){
            System.out.println("Invalid id");
            return;
        }

        System.out.println("Enter dateBeg");
        String dateBeg = in.next();
        if(!dateBeg.matches(timeRegex)){
            System.out.println("Invalid date");
            return;
        }

        System.out.println("Enter dateEnd");
        String dateEnd = in.next();
        if(!dateEnd.matches(timeRegex)){
            System.out.println("Invalid date");
            return;
        }

        System.out.println("Enter dateRet");
        String dateRet = in.next();
        if(! dateRet.matches(timeRegex)){
            System.out.println("Invalid date");
            return;
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("bookId", idBook);
        jsonObject.put("clientId", idClient);
        jsonObject.put("dateBeg", dateBeg);
        jsonObject.put("dateEnd", dateEnd);
        jsonObject.put("dateRet", dateRet);

        add("http://localhost:8080/journal/add", jsonObject);
    }

    public static void getJournalsByClientName(){
        String regex = "[а-яА-Я]+";

        System.out.println("Enter client first name:");
        String name = in.next();
        if(!name.matches(regex)){
            System.out.println("Invalid first name");
            return;
        }
        try {
            String url = "http://localhost:8080/journal/getByClientName/" + name;
            ResponseEntity<List> list = restTemplate.exchange(url, HttpMethod.GET, entity, List.class);
            for(Object obj : Objects.requireNonNull(list.getBody())){
                System.out.println(obj + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Jornals not found");
        }

        System.out.println("All good");
    }

    public static void getJournalByBookName(){
        String regex = "[а-яА-Я]+";
        System.out.println("Enter book name:");
        String name = in.next();
        if(!name.matches(regex)){
            System.out.println("Invalid first name");
            return;
        }
        try {
            String url = "http://localhost:8080/journal/getByBookName/" + name;
            ResponseEntity<List> list = restTemplate.exchange(url, HttpMethod.GET, entity, List.class);
            for(Object obj : Objects.requireNonNull(list.getBody())){
                System.out.println(obj + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Jornals not found");
        }
    }

    public static void getJournalByClientId(){
        String regex = "[0-9]+";
        System.out.println("Enter client id");
        String id = in.next();
        if(!id.matches(regex)){
            System.out.println("Invalid id");
            return;
        }
        try {
            String url = "http://localhost:8080/journal/getByClientId/" + id;
            ResponseEntity<List> list = restTemplate.exchange(url, HttpMethod.GET, entity, List.class);
            for (Object obj : Objects.requireNonNull(list.getBody())){
                System.out.println(obj + "\n");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void getClientById(){
        getById("http://localhost:8080/client/getById/");
    }

    public static void getClientByName(){
        String regex = "[а-яА-Я]+";
        System.out.println("Enter Client name");
        String name = in.next();
        if(!name.matches(regex)){
            System.out.println("Invalid name");
        }
        try{
            String url = "http://localhost:8080/client/getByName/" + name;
            ResponseEntity<Object> obj = restTemplate.exchange(url, HttpMethod.GET, entity, Object.class);
            System.out.println(obj.getBody().toString());
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void deleteClientById(){
        String regex = "[0-9]+";

        System.out.println("Enter id");
        String stringId = in.next();
        if(!stringId.matches(regex)){
            System.out.println("Invalid id");
            return;
        }
        int id = Integer.parseInt(stringId);

        try{
            String url = "http://localhost:8080/client/deleteById/" + id;
            restTemplate.exchange(url, HttpMethod.DELETE, entity, Object.class);
        } catch(Exception e){
            e.printStackTrace();
            System.out.println("Can't delete or not existing");
        }
    }

    public static void updateClientName() throws JSONException{
        String regex = "[0-9]+";
        String strRegex = "[а-яА-я]+";

        System.out.println("Enter id");
        String stringId = in.next();
        if(!stringId.matches(regex)){
            System.out.println("Invalid id");
            return;
        }
        int id = Integer.parseInt(stringId);
        System.out.println("Enter second name");
        String sname = in.next();
        if(!sname.matches(strRegex)){
            System.out.println("Invalid name");
            return;
        }
        System.out.println("Enter first name");
        String fname = in.next();
        if(!fname.matches(strRegex)){
            System.out.println("Invalid name");
            return;
        }
        System.out.println("Enter pather name");
        String pname = in.next();
        if(!pname.matches(strRegex)){
            System.out.println("Invalid name");
            return;
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("firstName", fname);
        jsonObject.put("secondName", sname);
        jsonObject.put("patherName", pname);

        HttpEntity request = new HttpEntity<>(jsonObject.toString(), header);
        try{
            String url = "http://localhost:8080/client/updateFullNameById/" + id;
            restTemplate.exchange(url,HttpMethod.PUT, request, Object.class);
            System.out.println("updated Client");
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("Something gone wrong");
        }
    }

    public static void addClient() throws JSONException{
        String regex = "[0-9]+";
        String strRegex = "[а-яА-я]+";
        System.out.println("Enter second name");
        String sname = in.next();
        if(!sname.matches(strRegex)){
            System.out.println("Invalid name");
            return;
        }
        System.out.println("Enter first name");
        String fname = in.next();
        if(!fname.matches(strRegex)){
            System.out.println("Invalid name");
            return;
        }
        System.out.println("Enter pather name");
        String pname = in.next();
        if(!pname.matches(strRegex)){
            System.out.println("Invalid name");
            return;
        }

        System.out.println("Eneter passport seria");
        String seria = in.next();
        if(!seria.matches(regex)){
            System.out.println("Invlid seria");
            return;
        }
        System.out.println("Eneter passport nubmer");
        String number = in.next();
        if(!number.matches(regex)){
            System.out.println("Invlid number");
            return;
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("first_name", fname);
        jsonObject.put("second_name", sname);
        jsonObject.put("pather_name", pname);
        jsonObject.put("passport_seria", seria);
        jsonObject.put("passport_num", number);

        add("http://localhost:8080/client/add", jsonObject);
    }

    public static void allBooks(){
        ResponseEntity<List> list = restTemplate.getForEntity("http://localhost:8080/book/getAll", List.class);
        for (Object obj : Objects.requireNonNull(list.getBody())){
            System.out.println(obj.toString() + ",\n");
        }
    }

    public static void getBookById(){
        getById("http://localhost:8080/book/getById/");
    }

    public static void getFirstBookByCntGreater(){
        getById("http://localhost:8080/book/getByCnt/");
    }

    public static void getBookByGenre(){
        String regex = "[а-яА-Я]+";
        System.out.println("Enter genre name");
        String name = in.next();
        if(!name.matches(regex)){
            System.out.println("Invalid name");
        }
        try{
            String url = "http://localhost:8080/book/getByGenre/" + name;
            ResponseEntity<List> list = restTemplate.exchange(url, HttpMethod.GET, entity, List.class);
            for (Object obj : Objects.requireNonNull(list.getBody())){
                System.out.println(obj.toString() + ",\n");
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void getBookByGenreId(){
        System.out.println("Enter Genre id:");
        String id = in.next();
        if (!id.matches("[0-9]+")) {
            System.out.println("Invalid id");
            return;
        }
        try{
            String url = "http://localhost:8080/book/getByGenreId/" + id;
            ResponseEntity<List> list = restTemplate.exchange(url, HttpMethod.GET, entity, List.class);
            for (Object obj : list.getBody()){
                System.out.println(obj);
            }
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Something gone wrong");
        }
    }

    public static void addBook() throws JSONException{
        String numRegex = "[0-9]+";
        String regex = "[а-яА-Я0-9a-zA-Z]+";
        System.out.println("Enter name");
        String name = in.next();
        if(!name.matches(regex)){
            System.out.println("Invalid name");
            return;
        }
        System.out.println("Enter cnt");
        String cnt = in.next();
        if(!cnt.matches(numRegex)){
            System.out.println("Invalid cnt");
            return;
        }
        System.out.println("Enter Genre id");
        String id = in.next();
        if(!id.matches(numRegex)){
            System.out.println("Invalid cnt");
            return;
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("cnt", cnt);
        jsonObject.put("bookTypeId", id);

        add("http://localhost:8080/book/add", jsonObject);
    }

    public static void allBookTypes(){
        ResponseEntity<List> list = restTemplate.getForEntity("http://localhost:8080/bookType/getAll", List.class);
        for (Object obj : Objects.requireNonNull(list.getBody())){
            System.out.println(obj.toString() + ",\n");
        }
    }

    public static void getBookTypeByName(){
        String regex = "[а-яА-Я]+";
        System.out.println("Enter genre name");
        String name = in.next();
        if(!name.matches(regex)){
            System.out.println("Invalid name");
        }
        try{
            String url = "http://localhost:8080/bookType/getByName/" + name;
            ResponseEntity<Object> obj = restTemplate.exchange(url, HttpMethod.GET, entity, Object.class);
            System.out.println(obj.getBody().toString());
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void getBookTypesByPrefix(){
        String regex = "[а-яА-Я]+";
        System.out.println("Enter prefix");
        String prefix = in.next();
        if(!prefix.matches(regex)){
            System.out.println("Invalid prefix");
            return;
        }
        try{
            String url = "http://localhost:8080/bookType/getByPrefix/" + prefix;
            ResponseEntity<List> list = restTemplate.getForEntity(url, List.class);
            for (Object obj : Objects.requireNonNull(list.getBody())){
                System.out.println(obj.toString());
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void addBookType() throws JSONException{
        String strRegex = "[a-zA-Zа-яА-Я0-9]+";
        String regex = "[0-9]+";
        System.out.println("Enter name:");
        String name = in.next();
        if(!name.matches(strRegex)){
            System.out.println("Invalid name");
            return;
        }
        System.out.println("Enter cnt:");
        String cnt = in.next();
        if(!cnt.matches(regex)){
            System.out.println("Invalid cnt");
            return;
        }
        System.out.println("Enter fine:");
        String fine = in.next();
        if(!fine.matches(regex)){
            System.out.println("Invalid fine");
            return;
        }
        System.out.println("Enter dayCount:");
        String dayCount = in.next();
        if(!dayCount.matches(strRegex)){
            System.out.println("Invalid dayCount");
            return;
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("cnt", cnt);
        jsonObject.put("fine", fine);
        jsonObject.put("dayCount", dayCount);

        add("http://localhost:8080//bookType/add", jsonObject);
    }

    public static void updateBookType() throws JSONException{
        String strRegex = "[a-zA-Zа-яА-Я0-9]+";
        String regex = "[0-9]+";
        System.out.println("Enter id:");
        String id = in.next();
        if(!id.matches(regex)){
            System.out.println("Invalid id");
            return;
        }
        System.out.println("Enter name:");
        String name = in.next();
        if(!name.matches(strRegex)){
            System.out.println("Invalid name");
            return;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);

        HttpEntity request = new HttpEntity<>(jsonObject.toString(), header);
        try{
            String url = "http://localhost:8080/bookType/updateName/" + id;
            restTemplate.exchange(url,HttpMethod.PUT, request, Object.class);
        } catch(Exception e){
            e.printStackTrace();
            System.out.println("Something gone wrong");
        }
    }

    public static void getCommands(){
        System.out.println("signIn");
        System.out.println("allJournals");
        System.out.println("getJournalById");
        System.out.println("getJournalByClientName");
        System.out.println("getJournalByBookName");
        System.out.println("getJournalByClientId");
        System.out.println("addJournal");
        System.out.println("getClientById");
        System.out.println("getClientByName");
        System.out.println("deleteClient");
        System.out.println("updateClientName");
        System.out.println("addClient");
        System.out.println("allBooks");
        System.out.println("getBookById");
        System.out.println("getFirstBookCntGreater");
        System.out.println("getBookByGenreName");
        System.out.println("getBookByGenreId");
        System.out.println("addBook");
        System.out.println("allBookTypes");
        System.out.println("getBookTypeByName");
        System.out.println("getBookTypeByPrefix");
        System.out.println("addBookType");
        System.out.println("updateBookTypeName");
    }

    public static void main(String[] args) {
        header.setContentType(MediaType.APPLICATION_JSON);
        System.out.println("Start Client. Print 'get commands' to see all available commands");
        String command = in.nextLine();
        while (!(command.equals("exit"))) {
            try {
                switch (command) {
                    case "get commands" -> getCommands();
                    case "signIn" -> signIn();
                    case "allJournals" -> getAllJournals();
                    case "getJournalById" -> getJournalById();
                    case "getJournalByClientName" -> getJournalsByClientName();
                    case "getJournalByBookName" -> getJournalByBookName();
                    case "getJournalByClientId" -> getJournalByClientId();
                    case "addJournal" -> addJournal();
                    case "getClientById" -> getClientById();
                    case "getClientByName" -> getClientByName();
                    case "deleteClient" -> deleteClientById();
                    case "updateClientName" -> updateClientName();
                    case "addClient" -> addClient();
                    case "allBooks" -> allBooks();
                    case "getBookById" -> getBookById();
                    case "getFirstBookCntGreater" -> getFirstBookByCntGreater();
                    case "getBookByGenreName" -> getBookByGenre();
                    case "getBookByGenreId" -> getBookByGenreId();
                    case "addBook" -> addBook();
                    case "allBookTypes" -> allBookTypes();
                    case "getBookTypeByName" -> getBookTypeByName();
                    case "getBookTypeByPrefix" -> getBookTypesByPrefix();
                    case "addBookType" -> addBookType();
                    case "updateBookTypeName" -> updateBookType();
                    case "" -> {}
                    default -> System.out.println("Invalid Command");
                }
            } catch (JSONException ex) {
                System.out.println("Entered invalid arguments");
            } catch (Exception e) {
                e.printStackTrace();
            }
            command = in.nextLine();
        }
    }
}