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
            text "Идет подсчёт..."
        RemoteData.Failure error ->
            text (toString error)
        RemoteData.Success result ->
            text (bmiToOutputString result.bmi)

bmiToOutputString : Float -> String
bmiToOutputString n =
    "ИМТ= " ++ (toString n) ++ "кг/м^2"