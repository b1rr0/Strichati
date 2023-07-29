package tokyo.ronin.tg.datebot.telegraph;

public class TelegraphStory {

    private String title = "";
    private String authorName = "";
    private String story;
    private TelegraphStoryBuilder telegraphStoryBuilder;

    public TelegraphStory withTitle(String title) {
        this.title = title;
        return this;
    }

    public TelegraphStory withAuthor(String authorName) {
        this.authorName = authorName;
        return this;
    }

    public TelegraphStoryBuilder create() {
        this.telegraphStoryBuilder = new TelegraphStoryBuilder();
        return telegraphStoryBuilder;
    }

    public void build() {
        story = telegraphStoryBuilder.build().toString();
    }

    public String getTitle() {
        return title;
    }

    public TelegraphStory setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getAuthorName() {
        return authorName;
    }

    public TelegraphStory setAuthorName(String authorName) {
        this.authorName = authorName;
        return this;
    }

    public String getStory() {
        return story;
    }

    public TelegraphStory setStory(String story) {
        this.story = story;
        return this;
    }

    public class TelegraphStoryBuilder {

        private final StringBuilder start = new StringBuilder("[");
        private final StringBuilder finish = new StringBuilder("]");
        private final StringBuilder current = new StringBuilder();

        public TelegraphStoryBuilder addWithStrong(String data) {
            String s = "\"" + data + "\"";
            current.append(doLog("strong", s));
            return this;
        }

        public TelegraphStoryBuilder addWithEm(String data) {
            String s = "\"" + data + "\"";
            current.append(doLog("em", s));
            return this;
        }

        public TelegraphStoryBuilder addWithP(String data) {
            String s = "\"" + data + "\"";
            current.append(doLog("p", s));
            return this;
        }

        public TelegraphStoryBuilder addWithStrongP(String data) {
            String s = "\"" + data + "\"";
            String s2 = doLog("strong", s);
            current.append(doLog("p", s2.substring(0, s2.length() - 1)));
            return this;
        }

        public TelegraphStoryBuilder addNewLane() {
            current.append(doLog("h1", "\n"));
            return this;
        }

        public TelegraphStoryBuilder addImage(String imageSrc) {
            current.append("{\n" +
                    "    \"tag\": \"img\",\n" +
                    "    \"attrs\": {\n" +
                    "      \"src\": \"" + imageSrc + "\"\n" +
                    "    }\n" +
                    "  },");
            return this;
        }

        private String doLog(String tag, String data) {
            return "{\n" +
                    "      \"tag\":\"" + tag + "\",\n" +
                    "      \"children\":[\n" +
                    "         " + data + "\n" +
                    "      ]\n" +
                    "   },";

        }

        public StringBuilder build() {
            if (!current.isEmpty()) {
                current.setLength(current.length() - 1);
            }
            start.append(current);
            start.append(finish);
            return start;
        }
    }
}