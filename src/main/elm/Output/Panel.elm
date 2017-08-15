module Output.Panel exposing (..)
import Html.Attributes exposing (class)
import Msgs exposing (Msg)
import Html exposing (..)
import Models exposing (HistoryItem)
import RemoteData exposing (WebData)

view : WebData HistoryItem -> Html Msg
view response =
    div [ class "col"]
        [ br [] []
        , h2 [] [text "Результат"]
        , br [] []
        , div [] [ maybeResult response
                 ]
        ]

maybeResult : WebData HistoryItem -> Html Msg
maybeResult response =
    case response of
        RemoteData.NotAsked ->
            text ""
        RemoteData.Loading ->
            text "Загрузка..."
        RemoteData.Failure error ->
            text (toString error)
        RemoteData.Success result ->
            resultView result


resultView : HistoryItem -> Html Msg
resultView item =
    div [ class "bigtext" ]
        [ b []
            [ text ("ИМТ = " ++ (toString item.bmi) ++ "кг/м")
            , sup []
                [ text "2" ]
            ]
        , text "  ("
        ,
            b []
                [ text item.kind ]
        , text ")"
        ]
