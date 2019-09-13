package ThreadCoreKnowledge.MultiThreadError;

import java.util.HashMap;
import java.util.Map;

//发布溢出
public class PrivateOverflow {

 private Map<String,String> state;

    public PrivateOverflow() {
        state = new HashMap<>();
        state.put("1","周一");
        state.put("2","周二");
        state.put("3","周三");
        state.put("4","周四");
    }
    public Map<String ,String> getState(){
        return state;
    }

    public static void main(String[] args) {
        PrivateOverflow overflow =new PrivateOverflow();
        Map<String, String> stringMap = overflow.getState();
        System.out.println(stringMap.get("1"));
        stringMap.remove("1");
        System.out.println(stringMap.get("1"));
    }
}
