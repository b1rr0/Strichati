package tokyo.ronin.tg.datebot.telegraph.response;

public class AccountResponse {
    public class Result {
        private String short_name;
        private String author_name;
        private String author_url;
        private String access_token;
        private String auth_url;

        public String getShort_name() {
            return short_name;
        }

        public Result setShort_name(String short_name) {
            this.short_name = short_name;
            return this;
        }

        public String getAuthor_name() {
            return author_name;
        }

        public Result setAuthor_name(String author_name) {
            this.author_name = author_name;
            return this;
        }

        public String getAuthor_url() {
            return author_url;
        }

        public Result setAuthor_url(String author_url) {
            this.author_url = author_url;
            return this;
        }

        public String getAccess_token() {
            return access_token;
        }

        public Result setAccess_token(String access_token) {
            this.access_token = access_token;
            return this;
        }

        public String getAuth_url() {
            return auth_url;
        }

        public Result setAuth_url(String auth_url) {
            this.auth_url = auth_url;
            return this;
        }
    }

    private boolean ok;
    private Result result;

    public boolean isOk() {
        return ok;
    }

    public AccountResponse setOk(boolean ok) {
        this.ok = ok;
        return this;
    }

    public Result getResult() {
        return result;
    }

    public AccountResponse setResult(Result result) {
        this.result = result;
        return this;
    }
}
