package hello.itemservice.message;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.assertj.core.api.Assertions.*;

 @SpringBootTest
public class MessageSourceTest {

    @Autowired
    MessageSource ms;

    @Test
    void helloMessage(){
        Locale.setDefault(Locale.KOREA);//내 시스템이 영어라서 이렇게 설정을 해줘야지 정상적으로 출력됨
        String result = ms.getMessage("hello",null, null);
        assertThat(result).isEqualTo("안녕");
    }

    @Test
     void notFoundMessageCode(){
        assertThatThrownBy(()->ms.getMessage("no_code", null, null))
                .isInstanceOf(NoSuchMessageException.class);

    }
    @Test
     void notFoundMessageCodeDefaultMessage(){
        String result = ms.getMessage("no_code", null, "기본 메시지", null);
        assertThat(result).isEqualTo("기본 메시지");

    }

    @Test
     void argumentMessage(){
        Locale.setDefault(Locale.KOREA);//내 시스템이 영어라서 이렇게 설정을 해줘야지 정상적으로 출력됨
        String message = ms.getMessage("hello.name", new Object[]{"Spring"}, null);
        assertThat(message).isEqualTo("안녕 Spring");
    }

    @Test
     void defualtLang(){
        Locale.setDefault(Locale.KOREA);//내 시스템이 영어라서 이렇게 설정을 해줘야지 정상적으로 출력됨
        assertThat(ms.getMessage("hello", null, null)).isEqualTo("안녕");
        assertThat(ms.getMessage("hello", null, Locale.KOREA)).isEqualTo("안녕");
    }

    @Test
     void enLang(){
        assertThat(ms.getMessage("hello", null, Locale.ENGLISH)).isEqualTo("hello");
    }
}
