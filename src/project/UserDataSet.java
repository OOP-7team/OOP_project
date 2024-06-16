package project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDataSet {
    // 사용자 정보를 관리하는 클래스

	private static UserDataSet userDataSetInstance;
    private Map<String, User> users; // 사용자 정보를 저장할 HashMap
    
    // 생성자: HashMap을 초기화
    public UserDataSet() {
        users = new HashMap<>(); // 새로운 HashMap 인스턴스를 생성하여 users에 할당
    }
    
    public static UserDataSet getUserDataSetInstance() {
        if (userDataSetInstance == null) {
        	userDataSetInstance = new UserDataSet();
        }
        return userDataSetInstance;
    }

    // 사용자 정보를 추가하는 메서드
    public void addUser(User user) {
        users.put(user.getUserId(), user); // 사용자의 아이디를 키로, User 객체를 값으로 HashMap에 추가
    }

    // 아이디로 사용자 정보를 가져오는 메서드
    public User getUser(String userId) {
        return users.get(userId); // 아이디를 키로 사용하여 HashMap에서 User 객체를 반환
    }
    
    // grade와 className이 동일한 사용자들만 필터링하여 리스트로 반환하는 메서드
    public List<User> getUsersByGradeAndClass(String grade, String className) {
        List<User> filteredUsers = new ArrayList<>();
        for (User user : users.values()) {
            if (user.getGrade().equals(grade) && user.getClassName().equals(className)) {
                filteredUsers.add(user);
            }
        }
        return filteredUsers;
    }
    
   // 생년월일과 주민등록번호 뒷자리로 사용자 정보를 찾는 메서드
    public User findUserByBirthDateAndSecret(String birthDate, String secret) {
        for (User user : users.values()) {
            if (user.getBirthDate().equals(birthDate) && user.getSecret().equals(secret)) {
                return user;
            }
        }
        return null;
    }
    
    // 사용자 정보를 삭제하는 메서드
    public void deleteUser(String userId) {
        users.remove(userId); // 아이디를 키로 사용하여 HashMap에서 User 객체를 삭제
    }
   // 아이디 중복 검사
    public boolean isUserIdExists(String userId) {
        return users.containsKey(userId);
    }



}
