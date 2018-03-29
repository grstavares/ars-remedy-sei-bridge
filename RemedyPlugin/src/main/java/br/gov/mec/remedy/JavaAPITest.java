package br.gov.mec.remedy;

import com.bmc.arsys.api.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class JavaAPITest {
    private ARServerUser server;
    private String formName= "JavaAPITest";

    public JavaAPITest() {
        server = new ARServerUser();
        server.setServer("localhost");
        server.setUser("srv-remedy");
        server.setPassword("");
    }

//    public static void main(String[] args) {
//
//        JavaAPITest test = new JavaAPITest();
//        test.connect();
//
////        test.createEntry("Demo","1","test1");
////        test.createEntry("Demo","2","test2");
////        String entryID = test.createEntry("Demo","3","test3");
////        test.modifyEntry(entryID);
////        test.queryEntrysByID(entryID);
////        test.queryEntrysByQual("( \'Create Date\' > \"1/1/2004 12:00:00 AM\" )");
////        test.queryEntrysByQual("( \'Create Date\' > \"1/1/2010\" )");
//        test.cleanup();
//
//    }

    // Connect the current user to the server.
    void connect() {

        System.out.println();
        System.out.println("Connecting to AR Server...");

        try {
            server.verifyUser();

        } catch (ARException e) {
            //This exception is triggered by a bad server, password or,
            //if guest access is turned off, by an unknown username.
            ARExceptionHandler(e, "Cannot verify user " + server.getUser() + ".");
            System.exit(1);
        }

        System.out.println("Connected to AR Server " + server.getServer());
    }

    // Create an entry in a form using the given field values.
    public String createEntry (String submitter, String status,
                               String shortDesc) {
        String entryIdOut= "";
        try {

            Entry entry = new Entry();
            entry.put(Constants.AR_CORE_SUBMITTER, new Value(submitter));
            entry.put(Constants.AR_CORE_STATUS,new Value(status, DataType.ENUM));
            entry.put(Constants.AR_CORE_SHORT_DESCRIPTION,new Value(shortDesc));
            entryIdOut = server.createEntry(formName, entry);
            System.out.println();
            System.out.println("Entry created. The id # is " +entryIdOut);

        } catch (ARException e) {
            ARExceptionHandler(e, "Cannot create the entry." );
        }
        return entryIdOut;
    }

    // Modify the short description field on the specified entry.
    void modifyEntry(String entryId) {

        try {

            Entry entry = server.getEntry(formName, entryId, null);
            entry.put(Constants.AR_CORE_SHORT_DESCRIPTION,new Value("Modified by JavaAPITest"));
            server.setEntry(formName, entryId, entry, null, 0);
            System.out.println();
            System.out.println("Entry #" + entryId + " modified successfully.");

        }
        catch(ARException e) {
            ARExceptionHandler(e,"Cannot modify the entry. ");
        }
    }

    // Retrive an entry by its entry ID and print out the number of
    // fields in the entry. For each field in the entry, print out the
    // value, and the field info (name, id and the type).
    void queryEntrysByID(String entryId) {

        System.out.println();
        System.out.println("Retrieving entry with entry ID#" + entryId);

        try {

            Entry entry = server.getEntry(formName, entryId, null);

            QualifierInfo predicate = new QualifierInfo();

            if( entry  == null ){
                System.out.println("No data found for ID#" + entryId);
                return;
            } else
                System.out.println("Number of fields: " + entry.size());

            // Retrieve all properties of fields in the entry.
            Set<Integer> fieldIds = entry.keySet();
            for (Integer fieldId : fieldIds){
                Field field = server.getField(formName,fieldId.intValue());
                Value val = entry.get(fieldId);

                // Output field's name, value, ID, and type.
                System.out.print(field.getName().toString());
                System.out.print(": " + val);
                System.out.print(" , ID: " + field.getFieldID());
                System.out.print(" , Field type: " + field.getDataType());
                // Handle DateTime value.

                if ( field instanceof DateTimeField ){
                    System.out.print(", DateTime value: ");
                    Timestamp callDateTimeTS = (Timestamp)val.getValue();
                    if (callDateTimeTS != null)
                        System.out.print(callDateTimeTS.toDate());

                }
                System.out.println("");
            }
        } catch( ARException e ){
            ARExceptionHandler (e, "Problem while querying by entry ID.");
        }
    }

    // Retrieve entries from the form using the given qualification. With
    // the returned entry set, print out the ID of each entry and the
    // contents in its shortDescription field.
    void queryEntrysByQual(String qualStr) {
        System.out.println();
        System.out.println ("Retrieving entryies with qualification " + qualStr);

        try {
            // Retrieve the detail info of all fields from the form.
            List <Field> fields = server.getListFieldObjects(formName);
            // Create the search qualifier.
            QualifierInfo qual = server.parseQualification(qualStr, fields, null, Constants.AR_QUALCONTEXT_DEFAULT);

            int[] fieldIds = {2, 7, 8};
            OutputInteger nMatches = new OutputInteger();
            List<SortInfo> sortOrder = new ArrayList<SortInfo>();
            sortOrder.add(new SortInfo(2, Constants.AR_SORT_DESCENDING));
            // Retrieve entries from the form using the given
            // qualification.
            List<Entry> entryList = server.getListEntryObjects(
                    formName, qual, 0,
                    Constants.AR_NO_MAX_LIST_RETRIEVE,
                    sortOrder, fieldIds, true, nMatches);

            System.out.println ("Query returned " + nMatches + " matches.");

            if( nMatches.intValue() > 0){
                // Print out the matches.
                System.out.println("Request Id         " +
                        "Short Description" );
                for( int i = 0; i < entryList.size(); i++ ){
                    System.out.println
                            (entryList.get(i).getEntryId() +
                                    "     " +

                                    entryList.get(i).get(Constants.AR_CORE_SHORT_DESCRIPTION));
                }
            }
        } catch( ARException e ) {
            ARExceptionHandler(e, "Problem while querying by qualifier. ");
        }
    }

    public void ARExceptionHandler(ARException e, String errMessage){
        System.out.println(errMessage);
        printStatusList(server.getLastStatus());
        System.out.print("Stack Trace:");
        e.printStackTrace();
    }

    public void printStatusList(List<StatusInfo> statusList) {
        if (statusList == null || statusList.size()==0) {
            System.out.println("Status List is empty.");
            return;
        }
        System.out.print("Message type: ");
        switch(statusList.get(0).getMessageType())
        {
            case Constants.AR_RETURN_OK:
                System.out.println("Note");
                break;
            case Constants.AR_RETURN_WARNING:
                System.out.println("Warning");
                break;
            case Constants.AR_RETURN_ERROR:
                System.out.println("Error");
                break;
            case Constants.AR_RETURN_FATAL:
                System.out.println("Fatal Error");
                break;
            default:
                System.out.println("Unknown (" +
                        statusList.get(0).getMessageType() + ")");
                break;
        }
        System.out.println("Status List:");
        for (int i=0; i < statusList.size(); i++) {

            System.out.println(statusList.get(i).getMessageText());

            System.out.println(statusList.get(i).getAppendedText());
        }
    }

    public void cleanup() {
        // Logout the user from the server. This releases the resource
        // allocated on the server for the user.
        server.logout();
        System.out.println();
        System.out.println("User logged out.");
    }
}
