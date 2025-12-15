package com.innowise.webproject.dao.impl;

import com.innowise.webproject.connectionpool.PostgresConnectionPool;
import com.innowise.webproject.entity.impl.Bet;
import com.innowise.webproject.entity.impl.BetType;
import com.innowise.webproject.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BetDao {
    private static final Logger logger = LogManager.getLogger();
    private static final String INSERT_BET = "INSERT INTO bets (user_id, competition_id, bet_type, amount, coefficient, status) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_BY_ID = "SELECT * FROM bets WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM bets";
    private static final String SELECT_BY_USER = "SELECT * FROM bets WHERE user_id = ?";
    private static final String SELECT_BY_COMPETITION = "SELECT * FROM bets WHERE competition_id = ?";
    private static final String UPDATE_BET = "UPDATE bets SET bet_type=?, amount=?, coefficient=?, status=? WHERE id=?";
    private static final String DELETE_BET = "DELETE FROM bets WHERE id=?";
    private static final String UPDATE_STATUS = "UPDATE bets SET status=? WHERE id=?";

    public void addBet(Bet bet) throws DaoException {
        Connection connection = PostgresConnectionPool.getInstance().getConnection();
        try (PreparedStatement ps = connection.prepareStatement(INSERT_BET)) {
            ps.setInt(1, bet.getUserId());
            ps.setInt(2, bet.getCompetitionId());
            ps.setString(3, bet.getStringBetType());
            ps.setBigDecimal(4, bet.getAmount());
            ps.setDouble(5, bet.getCoefficient());
            ps.setString(6, bet.getStatus());
            ps.executeUpdate();
            logger.info("Bet added for user {} on competition {}", bet.getUserId(), bet.getCompetitionId());
        } catch (SQLException e) {
            throw new DaoException("Error adding bet", e);
        } finally {
            PostgresConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    public Optional<Bet> getBetById(int id) throws DaoException {
        Connection connection = PostgresConnectionPool.getInstance().getConnection();
        try (PreparedStatement ps = connection.prepareStatement(SELECT_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(mapBet(rs));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DaoException("Error retrieving bet by ID", e);
        } finally {
            PostgresConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    public List<Bet> getAllBets() throws DaoException {
        Connection connection = PostgresConnectionPool.getInstance().getConnection();
        List<Bet> bets = new ArrayList<>();
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(SELECT_ALL);
            while (rs.next()) {
                bets.add(mapBet(rs));
            }
        } catch (SQLException e) {
            throw new DaoException("Error retrieving all bets", e);
        } finally {
            PostgresConnectionPool.getInstance().releaseConnection(connection);
        }
        return bets;
    }

    public List<Bet> getBetsByUser(int userId) throws DaoException {
        Connection connection = PostgresConnectionPool.getInstance().getConnection();
        List<Bet> bets = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SELECT_BY_USER)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                bets.add(mapBet(rs));
            }
        } catch (SQLException e) {
            throw new DaoException("Error retrieving bets by user", e);
        } finally {
            PostgresConnectionPool.getInstance().releaseConnection(connection);
        }
        return bets;
    }

    public List<Bet> getBetsByCompetition(int competitionId) throws DaoException {
        Connection connection = PostgresConnectionPool.getInstance().getConnection();
        List<Bet> bets = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SELECT_BY_COMPETITION)) {
            ps.setInt(1, competitionId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                bets.add(mapBet(rs));
            }
        } catch (SQLException e) {
            throw new DaoException("Error retrieving bets by competition", e);
        } finally {
            PostgresConnectionPool.getInstance().releaseConnection(connection);
        }
        return bets;
    }

    public void updateBet(Bet bet) throws DaoException {
        Connection connection = PostgresConnectionPool.getInstance().getConnection();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_BET)) {
            ps.setString(1, bet.getStringBetType());
            ps.setBigDecimal(2, bet.getAmount());
            ps.setDouble(3, bet.getCoefficient());
            ps.setString(4, bet.getStatus());
            ps.setInt(5, bet.getId());
            ps.executeUpdate();
            logger.info("Bet updated: {}", bet.getId());
        } catch (SQLException e) {
            throw new DaoException("Error updating bet", e);
        } finally {
            PostgresConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    public void deleteBet(int id) throws DaoException {
        Connection connection = PostgresConnectionPool.getInstance().getConnection();
        try (PreparedStatement ps = connection.prepareStatement(DELETE_BET)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            logger.info("Bet deleted: {}", id);
        } catch (SQLException e) {
            throw new DaoException("Error deleting bet", e);
        } finally {
            PostgresConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    public void updateBetStatus(int id, String status) throws DaoException {
        Connection connection = PostgresConnectionPool.getInstance().getConnection();
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_STATUS)) {
            ps.setString(1, status);
            ps.setInt(2, id);
            ps.executeUpdate();
            logger.info("Bet status updated: {} -> {}", id, status);
        } catch (SQLException e) {
            throw new DaoException("Error updating bet status", e);
        } finally {
            PostgresConnectionPool.getInstance().releaseConnection(connection);
        }
    }

    private Bet mapBet(ResultSet rs) throws SQLException {
        Bet bet = new Bet();
        bet.setId(rs.getInt("id"));
        bet.setUserId(rs.getInt("user_id"));
        bet.setCompetitionId(rs.getInt("competition_id"));
        bet.setBetType(BetType.valueOf(rs.getString("bet_type")));
        bet.setAmount(rs.getBigDecimal("amount"));
        bet.setCoefficient(rs.getDouble("coefficient"));
        bet.setStatus(rs.getString("status"));
        return bet;
    }
}

