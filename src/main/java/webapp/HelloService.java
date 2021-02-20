package webapp;

import java.util.Optional;

public class HelloService {

    static final String FALLBACK_NAME = "world";
    static final Lang FALLBACK_LANG = new Lang(1L, "Hello", "en");

    private LangRepository repository;

    HelloService() {
        this(new LangRepository());
    }

    HelloService(LangRepository repository) {
        this.repository = repository;
    }


    String prepareGreeting(String name) {
        return prepareGreeting(name, null);
    }


    String prepareGreeting(String name, String lang) {
        Long langId = Optional.ofNullable(lang).map(Long::valueOf).orElse(FALLBACK_LANG.getId());

        String welcomeMsg = repository.findById(langId).orElse(FALLBACK_LANG).getWelcomeMsg();
        String nameToWelcome = Optional.ofNullable(name).orElse(FALLBACK_NAME);

        return welcomeMsg + " " + nameToWelcome + "!!";
    }

}
