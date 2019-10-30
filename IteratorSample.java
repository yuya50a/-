public class IteratorSample {
    public static void main(String[] args) {
        GameListAggregate gameListAggregate = new GameListAggregate();
        Iterator iterator = gameListAggregate.createIterator();
        gameListAggregate.add(new Game("グランツーリスモ", "SONY"));
        gameListAggregate.add(new Game("F1 2019", "Game Source Entertainment"));
        gameListAggregate.add(new Game("電車でGO!", "タイトー"));
        gameListAggregate.add(new Game("ぼくは航空管制官", "テクノブレイン"));
        iterator.first();
        while (!iterator.isDone()) {
            Game game = (Game) iterator.getItem();
            System.out.println(game.getName());
            iterator.next();
        }
    }
}
class Game {
    private String name; // 名称
    private String author; // 著者

    public Game(String name, String author) { // コンストラクタ
        this.name= name;
        this.author = author;
    }

    public String getName() { // 名称を取得
        return name;
    }

    public String getAuthor() { // 著者を取得
        return author;
    }
}
interface Iterator {
    public void first(); // 取り出し位置を最初の要素へ変える
    public void next(); // 取り出し位置を次の要素へ変える
    public boolean isDone(); // 取り出し位置が最後を超えたか？
    public Object getItem(); // 現在の取り出し位置から取り出す
}

class GameListIterator implements Iterator {
    private GameListAggregate aggregate;
    private int current;
    public GameListIterator(GameListAggregate aggregate) {
        this.aggregate = aggregate;
    }

    @Override
    public void first() {
        current = 0;
    }

    @Override
    public void next() {
        current += 1;
    }

    @Override
    public boolean isDone() {
        if (current >= aggregate.getNumberOfStock()) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Object getItem() {
        return aggregate.getAt(current);
    }
}
interface Aggregate {
    public Iterator createIterator();
}

class GameListAggregate implements Aggregate {
    private Game[] list = new Game[20];
    private int numberOfStock;

    @Override
    public Iterator createIterator() {
        return new GameListIterator(this);
    }

    public void add(Game game) {
        list[numberOfStock] = game;
        numberOfStock += 1;
    }

    public Object getAt(int number) {
        return list[number];
    }

    public int getNumberOfStock() {
        return numberOfStock;
    }
}