package entity;

public class TokenEnity {

    /**
     * access_token : 2dDa7uC950cLcqDBXIG_jwuTTiDK-cGwK0mPvIz5yCiI27NjdxKgPVSNke7foCDaV-VIB0H45-peCGFu9tHYkxniVfXl4DH-R47PgcSCT97d_50fFTN2TwFGjdtqmlSkW_2Q-zgrMf-tH_HMXFC1kTAwZUQxI-jXZhjkeGPY7ZdpAL09Pf6oec9IN0DZvDdojH4JsMnE0VmRGvgSj2FUjkdGHCYbKLPrd8TlU7U5_O3qFY67TRjgezvWciCVW2WQ_jJ6xF58DhNea_XfRzQqly2nIn-rXHAZuKRhGUWwf6o
     * token_type : bearer
     * expires_in : 86399
     */

    private String access_token;
    private String token_type;
    private int expires_in;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    @Override
    public String toString() {
        return "TokenEnity{" +
                "access_token='" + access_token + '\'' +
                ", token_type='" + token_type + '\'' +
                ", expires_in=" + expires_in +
                '}';
    }

}
