package project;

import java.util.HashMap;
import java.util.Map;

public class UserDataSet {
    // 사용자 정보를 관리하는 클래스

    private Map<String, User> users; // 사용자 정보를 저장할 HashMap

    // 생성자: HashMap을 초기화
    public UserDataSet() {
        users = new HashMap<>(); // 새로운 HashMap 인스턴스를 생성하여 users에 할당
    }

    // 사용자 정보를 추가하는 메서드
    public void addUser(User user) {
        users.put(user.getId(), user); // 사용자의 아이디를 키로, User 객체를 값으로 HashMap에 추가
    }

    // 아이디로 사용자 정보를 가져오는 메서드
    public User getUser(String id) {
        return users.get(id); // 아이디를 키로 사용하여 HashMap에서 User 객체를 반환
    }
}
