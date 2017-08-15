module History.List exposing (..)

import Html exposing (..)
import Html.Attributes exposing (class, href)
import Models exposing (HistoryItem)
import Msgs exposing (Msg)
import RemoteData exposing (WebData)


view : WebData (List HistoryItem) -> Html Msg
view response =
    div []
        [ maybeList response
        ]


maybeList : WebData (List HistoryItem) -> Html Msg
maybeList response =
    case response of
        RemoteData.NotAsked ->
            text ""

        RemoteData.Loading ->
            text "Loading..."

        RemoteData.Success items ->
            list items

        RemoteData.Failure error ->
            text (toString error)


list : List HistoryItem -> Html Msg
list historyItems =
    div [ class "p2" ]
        [ table [class "table"]
            [ thead [ class "thead-inverse"]
                [ tr []
                    [ th [] [ text "ID" ]
                    , th [] [ text "Время" ]
                    , th [] [ text "Возраст" ]
                    , th [] [ text "Пол" ]
                    , th [] [ text "Рост" ]
                    , th [] [ text "Вес" ]
                    , th [] [ text "ИМТ" ]
                    , th [] [ text "Индекс Пондера" ]
                    , th [] [ text "Тип" ]
                    ]
                ]
            , tbody [] (List.map historyRow historyItems)
            ]
        ]


historyRow : HistoryItem -> Html Msg
historyRow historyItem =
    tr []
        [ td [] [ text (toString historyItem.id) ]
        , td [] [ text historyItem.date ]
        , td [] [ text (toString historyItem.age) ]
        , td [] [ text (toString historyItem.gender) ]
        , td [] [ text (toString historyItem.height) ]
        , td [] [ text (toString historyItem.weight) ]
        , td [] [ text (toString historyItem.bmi) ]
        , td [] [ text (toString historyItem.pi) ]
        , td [] [ text (toString historyItem.kind) ]
        ]
