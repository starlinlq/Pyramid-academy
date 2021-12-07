package account.security;

import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class CompromisedPasswords implements CompromisedPassword {
    private final List<String> list = List.of("PasswordForJanuary", "PasswordForFebruary", "PasswordForMarch", "PasswordForApril",
            "PasswordForMay", "PasswordForJune", "PasswordForJuly", "PasswordForAugust",
            "PasswordForSeptember", "PasswordForOctober", "PasswordForNovember", "PasswordForDecember");

    @Override
    public boolean checkPassword(String password){
       return list.contains(password);
    }
}
