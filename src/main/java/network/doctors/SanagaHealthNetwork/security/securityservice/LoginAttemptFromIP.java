package network.doctors.SanagaHealthNetwork.security.securityservice;


import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
public class LoginAttemptFromIP {
    public static final  int MAX_FAILED_ATTEMPTS = 3;
//    private LoadingCache<String,  Integer> loginAttempts;
    private final LoadingCache<String, Integer> loginAttempts;

    @Autowired
    private HttpServletRequest httpServletRequest;

    public LoginAttemptFromIP(){
        super();
        loginAttempts = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.DAYS).build(new CacheLoader<String, Integer>() {
            @Override
            public Integer load(final String key) {
                return 0;
            }
        });
    }

    public void failedLogin(final  String key) {
        int attemptedAuth;

        try {
            attemptedAuth = loginAttempts.get(key);
        }catch (final ExecutionException e){
            attemptedAuth = 0;
        }
        attemptedAuth++;
        loginAttempts.put(key, attemptedAuth);
    }

    public boolean isUserBlocked(){
        try {
            return loginAttempts.get(getUserIP()) >= MAX_FAILED_ATTEMPTS;

        } catch (final ExecutionException e){
            return false;
        }
    }

    public String getUserIP() {
        final  String xfHeader = httpServletRequest.getHeader("X-Forwarded-for");
        if (xfHeader != null) {
            return xfHeader.split(",")[0];
        }
        return httpServletRequest.getRemoteAddr();
    }
}
