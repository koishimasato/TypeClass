# ref
[型クラスへの誘い · Scala研修テキスト](https://scalajp.github.io/scala_text/introduction-to-typeclass.html)

# 型クラスとは
ポリモルフィズムの一種

型によらず、共通の処理を型に持たせるために存在する1手段である。
Strategy戦略の一種

scalaで書く場合、implicit parameterとcontext boundの2通りの記述方法が存在

1. 型共通の型クラスを宣言
2. 実装部分では型クラスをimplicit parameterかcontext boundで追加する
3. 実装部分で、型クラスのメソッドを呼ぶ

メリットとして、型という余計な情報を実装部分（クライアント）で意識しないでかけるので、実装部分ではより処理の流れがわかりやすくなる。

型クラスの概念を応用するとモナドにできる
