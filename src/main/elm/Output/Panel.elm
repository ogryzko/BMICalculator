module Output.Panel exposing (..)
import Msgs exposing (Msg)
import Html exposing (..)
import Models exposing (ResultValue)
import RemoteData exposing (WebData)

view : WebData ResultValue -> Html Msg
view response =
    div []
        [ br [] []
        , h2 [] [text "Результат"]
        , br [] []
        , div [] [ maybeResult response
                 ]
        ]

maybeResult : WebData ResultValue -> Html Msg
maybeResult response =
    case response of
        RemoteData.NotAsked ->
            text ""
        RemoteData.Loading ->
            text "Идет подсчёт..."
        RemoteData.Failure error ->
            text (toString error)
        RemoteData.Success result ->
            text (toString result.bmi)