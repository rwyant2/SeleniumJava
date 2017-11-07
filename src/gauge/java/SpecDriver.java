package gauge.java;

//import java.lang.Runtime;
//import java.lang.Process;
//import java.io.BufferedReader;
//import java.io.InputStreamReader

// Let's see if I can kick off specs conditionally
// If everythingsSwell
//      proceed with execution
// else
//      flag as "pass"
// end if
//public class SpecDriver extends cases.PapaBless {
//    if(everythingsSwell) { // I'm able to fins all my options in PapaBless's constructor:
//        everythingsSwell = kickOffSmokes(); //kick off smoke test specs
//    }
//
//    if(everythingsSwell) {
//
//    }
//
//    try {
//        Runtime rt = Runtime.getRuntime();
//        Process pr = rt.exec("gauge run blah de blah"); // kick off smoke specs
//
//        BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
//
//        String line=null;
//
//        while((line=input.readLine()) != null) {
//            System.out.println(line);
//        }
//
//        int exitVal = pr.waitFor();
//        System.out.println("Exited with error code "+exitVal);
//
//    } catch(Exception e) {
//        System.out.println(e.toString());
//        e.printStackTrace();
//    }
//
//
//}
