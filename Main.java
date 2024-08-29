import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Test kodlarını buraya ekleyebilirsiniz.
    }

    class User {
        private String name;
        private HashMap<Integer, Post> posts; // Kullanıcının gönderileri
        private HashSet<User> following; // Takip edilen kullanıcılar
        private HashSet<Post> favorites; // Beğenilen Gönderiler
        private static int postCounter = 0; // Gönderi Sayacı

        public User(String name) {
            this.name = name;
            this.posts = new HashMap<>();
            this.following = new HashSet<>();
            this.favorites = new HashSet<>();
        }

        public String getName() {
            return name;
        }

        public Post getPost(int postId) {
            return posts.get(postId);
        }

        public void follow(User user) {
            following.add(user);
            System.out.println(name + ", " + user.getName() + " kullanıcısını takip ediyor");
        }

        public void createPost(String content) {
            Post newPost = new Post(postCounter++, this, content);
            posts.put(newPost.getId(), newPost);
            System.out.println(name + " yeni bir gönderi yayınladı: " + content);
        }

        public void addCommentToPost(User user, int postId, String comment) {
            Post post = user.getPost(postId);
            if (post != null) {
                post.addComment(new Comment(this, comment));
                System.out.println(name + ", " + user.getName() + "'in gönderisine yorum yaptı: " + comment);
            }
        }

        public void addToPostFavorites(User user, int postId) {
            Post post = user.getPost(postId);
            if (post != null) {
                favorites.add(post);
                System.out.println(name + ", " + user.getName() + "'in gönderisini beğendi: " + post.getContent());
            }
        }

        public void showFeed() {
            System.out.println("\n" + name + "'in Ana Sayfası");
            for (User user : following) {
                user.showPosts();
            }
        }

        public void showPosts() {
            System.out.println(name + "'in Gönderileri:");
            for (Post post : posts.values()) {
                System.out.println(post);
            }
        }
    }

    class Post implements Comparable<Post> {
        private int id;
        private User author;
        private String content;
        private LinkedList<Comment> comments; // Gönderinin yorumları
        private static int commentCounter = 0; // Yorum Sayacı

        public Post(int id, User author, String content) {
            this.id = id;
            this.author = author;
            this.content = content;
            this.comments = new LinkedList<>();
        }

        public int getId() {
            return id;
        }

        public String getContent() {
            return content;
        }

        public void addComment(Comment comment) {
            comments.add(comment);
        }

        @Override
        public int compareTo(Post other) {
            return Integer.compare(this.id, other.id);
        }

        @Override
        public String toString() {
            return "Gönderi ID: " + id + ", Yazar: " + author.getName() + ", İçerik: " + content;
        }
    }

    class Comment {
        private User author;
        private String content;

        public Comment(User author, String content) {
            this.author = author;
            this.content = content;
        }

        @Override
        public String toString() {
            return author.getName() + ": " + content;
        }
    }
}

