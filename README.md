# compose-graphql-example

## アーキテクチャ概要
<img align="right" width="420px" src="https://user-images.githubusercontent.com/8592167/167346296-2f756f98-8e14-4e2c-99a4-8b64ec109a5a.png">

- Screen  
  - 表示とイベント受付を担当 
  - フルJetpackComposeで構築
  - 監視するStateはトップレベルのComposableでのみ保持し各UIに反映
  - イベントは全てViewModelに渡す
- ViewModel
  - 状態（StateFlow）の保持と更新を担当
  - イベントを受けてDomain層に処理を依頼し状態を更新
  - プラットフォーム固有のロジック（UseCase）も一旦ここで
- Model
  - ビジネスロジックを担当
- Repository
  - 永続化層とのデータの入出力を担当
  - Domain層にInterface、実装はData層で定義することでデータフローと依存を逆転
  - 永続化層のデータはModelに変換して返す

<br clear="all" />

## モジュール構成と依存関係
<img align="right" width="480px" src="https://user-images.githubusercontent.com/8592167/167348934-416c6a37-d6fd-47eb-aa82-4cbb5193e4a2.png">

- クリーンアーキテクチャをベースとしたマルチモジュール構成に
- Ui/Data/Domainの水平方向の分割と、UI、Data内の垂直方向の分割の組み合わせ
- モジュール分割と実装Classのinternal化で非推奨の依存はコンパイルエラーに仕向ける
- GraphQL（Apollo）には依存させずSchemaはRepository実装モジュールに閉じる方針とする

<br clear="all" />

## 画面構成と状態スコープ
<img align="right" width="640px" src="https://user-images.githubusercontent.com/8592167/167349015-56a43676-cd0c-4569-bc17-02699088ae1d.png">

- シングルActivity + JetpackCompose
- 画面構成と遷移はNavigationコンポーネントで定義
- Hilt x NavigationComposeで、状態（UiState of ViewModel）をActivity or NavigationGraph（Screen）にスコープ

<br clear="all" />
