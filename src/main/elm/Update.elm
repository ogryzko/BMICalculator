module Update exposing (..)

import Models exposing (Model)
import Msgs exposing (Msg)
import Commands exposing (calculateCmd, fetchHistory)


update : Msg -> Model -> ( Model, Cmd Msg )
update msg model =
    let
        oldInput =
            model.input
    in
    case msg of
        Msgs.NoOp ->
            ( model, Cmd.none )

        Msgs.Age age ->
            let
                intAge =
                    Result.withDefault 0 <| String.toInt age
            in
            ( { model | input = { oldInput | age = intAge } }, Cmd.none )

        Msgs.Height h ->
            let
                height =
                    Result.withDefault 0 <| String.toFloat h
            in
            ( { model | input = { oldInput | height = height } }, Cmd.none )

        Msgs.Weight w ->
            let
                weight =
                    Result.withDefault 0 <| String.toFloat w
            in
            ( { model | input = { oldInput | weight = weight } }, Cmd.none )
        Msgs.Calculate ->
            ( { model | input = { oldInput | isSubmitted = True } }, calculateCmd model.input )

        Msgs.SetGender gender ->
            ( { model | input = { oldInput | gender = gender } }, Cmd.none )

        Msgs.OnFetchHistory response ->
            ( { model | history = response }, Cmd.none )
        Msgs.OnGetResult response ->
            ({model | result = response}, fetchHistory)

